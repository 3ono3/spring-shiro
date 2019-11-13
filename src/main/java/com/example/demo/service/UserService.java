package com.example.demo.service;

import com.example.demo.entity.SysRole;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author GuoJingyuan
 * @date 2019/10/9
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<UserInfo> findAll() {
        List<UserInfo> userInfos = userRepository.findAll();
        return userInfos;
    }

    public UserInfo findById(Integer id) {
        Optional<UserInfo> userInfOptional = userRepository.findById(id);
        return userInfOptional.get();
    }

    public UserInfo editUser(Integer id, String name, String password) {
        UserInfo userInfo = userRepository.getUserInfoByUid(id);
        userInfo.setName(name);
        userInfo.setPassword(password);
        userRepository.save(userInfo);
        return userInfo;
    }

    public UserInfo addUser(String name, String userName, String password, HashSet<Integer> roleIds) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setUsername(userName);
        userInfo.setPassword(password);
        int roleSize = roleIds.size();
        HashSet<SysRole> userRoles = new HashSet<SysRole>((int) (roleSize/0.7 + 1));
        for (Integer roleId : roleIds) {
            SysRole sysRole = roleRepository.getSysRoleById(roleId);
            userRoles.add(sysRole);
        }
        userInfo.setRoles(userRoles);
        userRepository.save(userInfo);
        return userInfo;
    }

    public Integer deleteUser(Integer id) {
        return  userRepository.deleteByUid(id);
    }
}
