package com.zjmx.elastic;

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
		System.out.println(sysUserRepository.findAll());
	}

}
