package com.zjmx.elastic.repository;

import com.zjmx.elastic.domain.SysConfig;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 *扩展特定模块的库
 */
public interface SysConfigRepository extends MyBaseRepository<SysConfig,Integer> {

}
@NoRepositoryBean
interface MyBaseRepository<T,ID> extends PagingAndSortingRepository<T,ID>{
    /**
     * 定制的存储库
     * @param status 状态
     * @return
     */
    List<T> querySysConfigByStatusEquals(Integer status);
}