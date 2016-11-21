package com.luv2code.springdemo;

public class LazyCoach implements Coach {

	private FortuneService fortuneService;
	
	public LazyCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Go home";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
