package io.github.monthalcantara.CatalogoVideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CatalogoVideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoVideoApplication.class, args);
	}

}
