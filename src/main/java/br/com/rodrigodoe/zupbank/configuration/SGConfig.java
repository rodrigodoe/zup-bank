package br.com.rodrigodoe.zupbank.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SGConfig {

	@Value("${SENDGRID.API.KEY}")
	private String API_KEY;

	@Bean
	public SendGrid sgClient() {
		return new SendGrid(API_KEY);
	}

}
