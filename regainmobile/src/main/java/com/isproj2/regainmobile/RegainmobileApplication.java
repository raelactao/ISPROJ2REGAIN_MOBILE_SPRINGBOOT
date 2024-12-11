package com.isproj2.regainmobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RegainmobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegainmobileApplication.class, args);
	}

}
