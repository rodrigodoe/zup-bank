package br.com.rodrigodoe.zupbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ZupBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZupBankApplication.class, args);
	}

}
