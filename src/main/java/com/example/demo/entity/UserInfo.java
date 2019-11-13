package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author GuoJingyuan
 * @date 2019/9/24
 */
@Entity
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Integer uid;
    //账号
    @Column(unique = true)
    private String username;
    //昵称
    private String name;
    private String password;
    private byte state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<SysRole> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
