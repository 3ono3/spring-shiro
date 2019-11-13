package com.example.demo.repository;

import com.example.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author GuoJingyuan
 * @date 2019/9/25
 */
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    @Transactional
    UserInfo getUserInfoByUsername(String userName);

    @Transactional
    UserInfo getUserInfoByUsernameAndPassword(String userName, String Password);

    @Transactional
    UserInfo getUserInfoByUid(Integer uid);

    @Transactional
    int deleteByUid(Integer uid);
}
