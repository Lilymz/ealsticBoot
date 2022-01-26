package com.zjmx.elastic.repository;

import com.zjmx.elastic.domain.SysUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SysUserRepository extends PagingAndSortingRepository<SysUser,Integer> {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<SysUser> querySysUserByUserNameEquals(String username);
}
