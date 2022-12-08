package com.check.security.config;

import com.check.common.constant.ConstantString;
import com.check.common.util.RedisUtils;
import com.check.security.utils.JwtUtils;
import com.check.system.mapper.SysMenuMapper;
import com.check.system.mapper.SysRoleMapper;
import com.check.system.mapper.SysUserMapper;
import com.check.security.pojo.bean.User;
import com.check.security.utils.EmptyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zzc
 */
@Component
public class JwtUserServiceImpl  implements UserDetailsService {

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysMenuMapper menuMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户基本信息
        User user;

        if (RedisUtils.contain(ConstantString.REDIS_USER + username)) {
            user = (User) RedisUtils.getValue(ConstantString.REDIS_USER + username);
        } else {
            user = userMapper.getSecurityUser(username);

            if (EmptyUtil.isNotEmpty(user)) {
                // 用户角色列表
                user.setRoles(roleMapper.getUserRoles(user.getCode()));
                // 用户菜单列表
                user.setMenus(menuMapper.getUserMenus(user.getCode()));
                // token
                String token = jwtUtils.generateToken(username);
                //String token = Base64.getEncoder().encodeToString((user.getUsername() + "_" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
                user.setToken(token);
                // 将用户信息保存在缓存中
                RedisUtils.saveValue(ConstantString.REDIS_USER + username, user, jwtProperties.getTokenValidityInSeconds(), TimeUnit.MINUTES);
            }
        }
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}