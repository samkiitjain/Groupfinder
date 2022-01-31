package com.tradeshift.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.tradeshift")
@EnableJpaRepositories({"com.tradeshift.repository"})
@EntityScan("com.tradeshift.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private void loadTestDatainDB(int numOfRow) {
		int rootGroupHeight = 0;
		String rootNodeID = "0";
		//String root
	}
}
