package com.zjmx.elastic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sys_config",schema="renren_fast")
public class SysConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="param_key")
    private String paramKey;

    @Column(name="param_value")
    private String paramValue;

    @Column(name="status")
    private Integer status ;

    @Column(name="remark")
    private String remark;
}
