package com.example.demo.repository;

import com.example.demo.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author GuoJingyuan
 * @date 2019/10/9
 */
public interface RoleRepository extends JpaRepository<SysRole, Integer> {
    /**
     * 根据id获取一条数据
     * @param id
     * @return
     */
    @Transactional
    SysRole getSysRoleById(Integer id);
}
