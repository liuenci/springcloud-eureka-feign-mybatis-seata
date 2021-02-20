package com.cier.seata.account;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@Slf4j
@SpringBootApplication
@MapperScan("com.cier.seata.account.dao")
@EnableEurekaClient
@EnableFeignClients
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
		log.info("[seata-account] AccountApplication 启动成功.");
	}

}
