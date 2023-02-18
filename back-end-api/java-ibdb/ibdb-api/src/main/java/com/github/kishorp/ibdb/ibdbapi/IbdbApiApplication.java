package com.github.kishorp.ibdb.ibdbapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * <li> Internet-Book-Database is a database of all the published books. This is an API to perform operations on IBDb </li>
 */
@SpringBootApplication
@ComponentScan({"com.github.kishorp.ibdb.ibdbapi.*", "com.github.kishorp.ibdb.ibdbservice.*"})
@EnableMongoRepositories({"com.github.kishorp.ibdb.ibdbdomain.repos.*"})
@OpenAPIDefinition(info = @Info(
		title = "IBDb API",
		version = "0.0",
		description = "Internet-Book-Database is a database of all the published books. This is an API to perform operations on IBDb",
		contact = @Contact(url = "https://github.com/kishor-p/IBDb", name = "Kishor Prakash", email = "kishor.p.15389@gmail.com"))
)
public class IbdbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbdbApiApplication.class, args);
	}

	/**
	 * Creating {@link ModelMapper} bean to be able to available for Dependency Injection in service layers
	 *
	 * @return created ModelMapper instance
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
