package com.example.demo.service.shiro;


import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author GuoJingyuan
 * @date 2019/9/25
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.err.println("权限认证");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        Set<SysRole> userRoles = userInfo.getRoles();
        for (SysRole sysRole : userRoles) {
            authorizationInfo.addRole(sysRole.getRole());
            for (SysPermission permission : sysRole.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.err.println("登录认证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        String passwordStr = new String(password);
        UserInfo userInfo = userRepository.getUserInfoByUsernameAndPassword(userName, passwordStr);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), getName());
        clearAuthorizationInfoCache(userInfo);
        return authenticationInfo;
    }

    /**
     * 清除指定用户的缓存
     * @param user
     */
    private void clearAuthorizationInfoCache(UserInfo user) {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        cache.remove(user.getUid());
    }
}
