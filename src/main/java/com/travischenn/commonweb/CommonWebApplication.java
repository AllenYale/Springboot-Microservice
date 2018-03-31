package com.travischenn.commonweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.travischenn.commonweb.repository")
@SpringBootApplication
public class CommonWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommonWebApplication.class, args);
	}
}
