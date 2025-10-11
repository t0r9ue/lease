package com.lease;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
@SpringBootApplication
@MapperScan("com.lease.web.*.mapper")
public class AdminWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminWebApplication.class, args);
	}

}
