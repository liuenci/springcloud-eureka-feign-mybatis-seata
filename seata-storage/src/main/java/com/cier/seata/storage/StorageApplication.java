package com.cier.seata.storage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@Slf4j
@SpringBootApplication
@MapperScan("com.cier.seata.storage.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class StorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);
		log.info("[seata-storage] StorageApplication 启动成功.");
	}



}
