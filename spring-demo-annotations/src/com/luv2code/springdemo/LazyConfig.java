package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LazyConfig {

	@Bean
	public FortuneService easyFortuneService() {
		return new EasyFortuneService();
	}
	
	@Bean
	public Coach lazyCoach() {
		return new LazyCoach(easyFortuneService());
	}
	
}
