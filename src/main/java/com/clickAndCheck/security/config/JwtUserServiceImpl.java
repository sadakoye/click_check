package com.clickAndCheck.security.config;

import com.clickAndCheck.security.mapper.SysMenuMapper;
import com.clickAndCheck.security.mapper.SysRoleMapper;
import com.clickAndCheck.security.mapper.SysUserMapper;
import com.clickAndCheck.security.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzc
 */
@Component
public class JwtUserServiceImpl  implements UserDetailsService {

    private final PasswordEncoder passwordEncoder = passwordEncoder();

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
        List<GrantedAuthority> list = new ArrayList<>();
        ///设置admin角色 角色前面加 ROLE_
        //list.add(new SimpleGrantedAuthority("ROLE_admin"));

        // 用户基本信息
        User user;

        if (userCache.containsKey(username)) {
            user = userCache.get(username);
        } else {
            user = userMapper.getSecurityUser(username);

            if (EmptyUtil.isNotEmpty(user)) {

                // 用户角色列表
                user.setRoles(roleMapper.getUserRoles(user.getUserId()));
                // 用户菜单列表
                user.setMenus(menuMapper.getUserMenus(user.getUserId()));
                // token
                String token = Base64.getEncoder().encodeToString((user.getUsername() + "_" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
                user.setToken(token);
                // 将用户信息保存在缓存中
                userCache.put(username, user);
            }
        }


        return new JwtUser(
                username,
                passwordEncoder.encode("123"),
                list
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}