package com.check.security.service.impl;

import com.check.common.config.properties.CasConfig;
import com.check.common.constant.ConstantException;
import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.HttpClientUtils;
import com.check.common.util.RedisUtils;
import com.check.security.config.JwtUserServiceImpl;
import com.check.security.pojo.bean.User;
import com.check.security.service.LoginService;
import com.check.security.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;

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
     * 门户cas登录
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
}
