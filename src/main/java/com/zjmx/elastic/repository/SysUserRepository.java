package com.zjmx.elastic.repository;

import com.zjmx.elastic.domain.SysUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysUserRepository extends PagingAndSortingRepository<SysUser,Integer> {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUser querySysUserByUserNameEquals(String username);
}
