package com.check.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.check.common.config.properties.CasConfig;
import com.check.common.constant.ConstantException;
import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.GzcssTokenUtils;
import com.check.common.util.HttpClientUtils;
import com.check.common.util.RedisUtils;
import com.check.security.config.JwtUserServiceImpl;
import com.check.security.pojo.bean.User;
import com.check.security.service.LoginService;
import com.check.security.utils.JwtUtils;
import com.check.system.mapper.SysUserMapper;
import com.check.system.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzc
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private JwtUserServiceImpl jwtUserService;

    @Resource
    JwtUtils jwtUtils;

    @Resource
    CasConfig casConfig;

    @Resource
    SysUserMapper userMapper;

    /**
     * 登录
     *
     * @param request     request
     * @param response    response
     * @param filterChain filterChain
     * @return Result<User>
     * @author zzc
     */
    @Override
    public Result<User> login(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        return Result.success();
    }

    /**
     * 登出
     *
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> logout() {
        User user = jwtUtils.getUser();
        if (RedisUtils.deleteValue(ConstantString.REDIS_USER + user.getUsername())) {
            return Result.success();
        }
        throw ConstantException.SYSTEM_LOGIN_OUT_ERROR;
    }

    /**
     * CAS-门户登录
     *
     * @param ticket   票据
     * @param request  request
     * @param response response
     * @return String
     * @author zzc
     */
    @Override
    public String casLogin(String ticket, HttpServletRequest request, HttpServletResponse response) {
        log.info("cas登录：ticket=" + ticket);

        String casUrl = casConfig.getCasUrl();
        String service = casConfig.getService();
        String loginSuccess = casConfig.getLoginSuccess();
        String loginError = casConfig.getLoginError();
        String loginIp = casConfig.getLoginIp();

        //未登录
        if (ticket == null) {
            return "redirect:" + casUrl + "/cas/login?service=" + service;
        }

        StringBuilder sb = new StringBuilder(casUrl);
        sb.append("/cas/serviceValidate");
        sb.append("?service=").append(service);
        sb.append("&ticket=").append(ticket);
        try {
            String result = HttpClientUtils.httpGetString(sb.toString(), null);
            Document document = DocumentHelper.parseText(result);
            //获取根节点元素对象
            Element root = document.getRootElement();
            Element successObj = root.element("authenticationSuccess");
            if (successObj != null) {
                String casName = successObj.elementText("user");

                if (loginIp != null) {

                    //根据用户登录请求 获得code
                    User user = (User) jwtUserService.loadUserByUsername(casName);
                    if (null == user) {
                        return "redirect:" + loginError + "?code=400&description=" + URLEncoder.encode("用户不存在", "UTF-8");
                    }
                    String token = user.getToken();

                    response.setHeader("Authorization", token);

                }
                //验证通过跳转页面
                log.info("cas验证通过，登录成功");
                return "redirect:" + loginSuccess;
            } else {
                //cas验证失败
                Element failureObj = root.element("authenticationFailure");
                String code = failureObj.attributeValue("code");
                String description = failureObj.getText();
                log.error("cas验证失败：code=" + code + "description=" + description);
                return "redirect:" + loginError + "?code=" + code + "&description=" + description;
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String infoMsg = sw.toString();
            log.error("cas登录失败：" + infoMsg);
            return "redirect:" + loginError + "?description=" + e.getMessage();
        }
    }

    /**
     * CAS-从门户系统同步用户
     *
     * @param request request
     * @return String
     * @author zzc
     */
    @Override
    public Result<Object> casAutoUser(HttpServletRequest request) {
        JSONArray casDataList = getCasDataList("/api/portal/users/all", getParamByRequest(request));
        if (casDataList != null) {
            //执行业务处理
            initUser(casDataList);
        }
        return Result.success();
    }

    public Map<String, String> getParamByRequest(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> params = new LinkedHashMap<>();
        for (String key : parameterMap.keySet()) {
            if (ConstantString.LOGIN_NAME.equals(key)
                    || ConstantString.SOURCE.equals(key)
                    || ConstantString.TIMESTAMP.equals(key)
                    || ConstantString.TOKEN.equals(key)
                    || ConstantString.SIGN.equals(key)
                    || ConstantString.ENCRYPTION_DATA.equals(key)) {
                continue;
            }
            String[] values = parameterMap.get(key);
            if (null != values && values.length >= 1 && null != values[0]) {
                params.put(key, values[0]);
            }
        }
        return params;
    }

    public JSONArray getCasDataList(String path, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(casConfig.getUrl() + casConfig.getUrlHead() + path);
        int index = 0;
        //设置参数
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (index == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                index++;
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key).append("=").append(value);
            }
        }
        Map<String, String> heads = createHead();
        JSONObject object = HttpClientUtils.httpGet(sb.toString(), heads);
        if (object != null) {
            if (ConstantString.TRUE.equals(object.getString(ConstantString.SUCCESS))) {
                JSONArray result = object.getJSONArray("result");
                log.info("请求获取成功：" + sb);
                return result;
            } else {
                return null;
            }
        }
        return null;
    }

    public Map<String, String> createHead() {
        HashMap<String, String> heads = new HashMap<>(4);
        Long timestamp = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString();
        Map<String, Object> payload = new LinkedHashMap<>();
        //系统id
        payload.put("appId", casConfig.getAppId());
        //签注时间
        payload.put("timestamp", timestamp);
        //随机数
        payload.put("nonce", nonce);
        payload.put("secret", casConfig.getAppKey());
        String signature = GzcssTokenUtils.sM3Encode(JSON.toJSONString(payload));
        heads.put("appid", casConfig.getAppId());
        heads.put("timestamp", timestamp.toString());
        heads.put("nonce", nonce);
        heads.put("signature", signature);
        log.info("发送请求头payload" + payload);
        log.info("发送请求头heads" + heads);
        return heads;
    }

    public void initUser(JSONArray userList) {

        log.info("从门户查询到用户数据" + userList.size() + "条");

        AtomicInteger success = new AtomicInteger();
        AtomicInteger error = new AtomicInteger();

        userList.forEach(o -> {
            JSONObject json = (JSONObject) o;
            if (json != null && ConstantString.ONE.equals(json.getString("status")) &&
                    ConstantString.ZERO.equals(json.getString("delFlag"))) {
                SysUser user = new SysUser();
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                user.setPassword(passwordEncoder.encode("123456"));
                user.setUsername(json.getString("userName"));
                user.setCasId(json.getString("id"));
                user.setNickName(json.getString("realName"));
                user.setCreateBy("CAS");
                user.setUpdateBy("CAS");
                user.setIdCard(json.getString("idCard"));
                user.setPhone(json.getString("phone"));
                user.setEmail(json.getString("email"));
                user.setGender(json.getString("gender"));
                user.setAvatarPath(json.getString("avatar"));
                try {
                    userMapper.insert(user);
                    success.getAndIncrement();
                } catch (Exception e) {
                    error.getAndIncrement();
                }
            }
        });
        log.info("同步用户成功" + success + "条， 失败" + error + "条");
    }
}
