package com.zjmx;

import com.zjmx.jpa.domain.SysUser;
import com.zjmx.jpa.practice.Demo;
import com.zjmx.jpa.repository.SysConfigRepository;
import com.zjmx.jpa.repository.SysUserRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.function.BiFunction;

@SpringBootApplication
public class ElasticBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ElasticBootApplication.class, args);
		RestHighLevelClient bean = run.getBean(RestHighLevelClient.class);
		SysUserRepository sysUserRepository = run.getBean(SysUserRepository.class);
		SysConfigRepository sysConfigRepository = run.getBean(SysConfigRepository.class);
		List<SysUser> admin = sysUserRepository.querySysUserByUserNameEquals("admin");
		Demo demo = run.getBean(Demo.class);
		BiFunction<Double, Double, Double> doubleDoubleDoubleBiFunction = demo.getFunctionMap().get("*");
		Double apply = doubleDoubleDoubleBiFunction.apply(5.0, 6.5);
		System.out.println(apply);
		BiFunction<Double, Double, Double> doubleDoubleDoubleBiFunction1 = demo.getFunctionMap().get("/");
		Double apply1 = doubleDoubleDoubleBiFunction1.apply(6.0, 3.0);
		System.out.println(apply1);
	}
}
