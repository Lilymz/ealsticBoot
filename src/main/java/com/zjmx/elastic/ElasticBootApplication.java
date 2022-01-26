package com.zjmx.elastic;

import com.zjmx.elastic.domain.SysConfig;
import com.zjmx.elastic.domain.SysUser;
import com.zjmx.elastic.repository.SysConfigRepository;
import com.zjmx.elastic.repository.SysUserRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ElasticBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ElasticBootApplication.class, args);
		RestHighLevelClient bean = run.getBean(RestHighLevelClient.class);
		SysUserRepository sysUserRepository = run.getBean(SysUserRepository.class);
		SysConfigRepository sysConfigRepository = run.getBean(SysConfigRepository.class);
		SysUser admin = sysUserRepository.querySysUserByUserNameEquals("admin");
		SysConfig sysConfig = sysConfigRepository.querySysConfigByStatusEquals(0);
		System.out.println(sysConfig.getParamValue());
	}
}
