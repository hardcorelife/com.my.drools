package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SpringbootDrools {
	public static void main(String[] args) {
		//默认情况下，date-effective 可接受的日期格式为“dd-MMM-yyyy” 修改默认的时间格式为"yyyy-MM-dd"
		System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm:ss");

		SpringApplication.run(SpringbootDrools.class, args);

	}
}
