package com.github.kishorp.ibdb.ibdbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.github.kishorp.ibdb.ibdbapi.*", "com.github.kishorp.ibdb.ibdbservice.*"})
public class IbdbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbdbApiApplication.class, args);
	}

}
