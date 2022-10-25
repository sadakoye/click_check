package com.click_check.security.config;

import com.click_check.security.mapper.SysMenuMapper;
import com.click_check.security.mapper.SysRoleMapper;
import com.click_check.security.mapper.SysUserMapper;
import com.click_check.security.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzc
 */
@Component
public class JwtUserServiceImpl  implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 用户信息缓存
     */
    static Map<String, User> userCache = new ConcurrentHashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户基本信息
        User user;

        if (userCache.containsKey(username)) {
            user = userCache.get(username);
        } else {
            user = userMapper.getSecurityUser(username);

            if (EmptyUtil.isNotEmpty(user)) {

                // 用户角色列表
                user.setRoles(roleMapper.getUserRoles(user.getCode()));
                // 用户菜单列表
                user.setMenus(menuMapper.getUserMenus(user.getCode()));
                // token
                String token = Base64.getEncoder().encodeToString((user.getUsername() + "_" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
                user.setToken(token);
                // 将用户信息保存在缓存中
                userCache.put(username, user);
            }
        }

        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}