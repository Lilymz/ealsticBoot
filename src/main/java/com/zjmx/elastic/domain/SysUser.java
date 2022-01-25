package com.zjmx.elastic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class SysUser {
    @Id
    private Integer userId;

    private String userName;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private Integer status;
}
