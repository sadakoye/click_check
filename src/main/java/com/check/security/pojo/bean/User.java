package com.check.security.pojo.bean;

import com.check.security.utils.EmptyUtil;
import com.check.system.pojo.vo.MenuVo;
import com.check.system.pojo.vo.RoleVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 1、定义spring security专用用户类
 * 必须实现UserDetails,并重写那几个方法
 * 这个类属性的获取是通过实现UserDetailsService接口的类中的loadUserByUsername()方法获取
 *
 * @author zzc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * token
     */
    private String token;

    /**
     * 用户code
     */
    private String code;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 手机号码号码
     */
    private String phone;

    /**
     * 包含的角色
     */
    private List<RoleVo> roles;

    /**
     * 拥有的菜单权限
     */
    private List<MenuVo> menus;

    /**
     * 将用户的菜单作为权限
     *
     * @return Collection
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(menus)) {
            for (MenuVo menu : menus) {
                auth.add(new SimpleGrantedAuthority(menu.getTitle()));
            }
        }
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户是否未过期
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否未锁定
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户凭证是否未过期
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否启用
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}