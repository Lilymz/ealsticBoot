package com.zjmx.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name="sys_user",schema = "renren_fast")
public class SysUser {
    @Id
    private Integer userId;

    @Column(name="username")
    private String userName;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private Integer status;
}
