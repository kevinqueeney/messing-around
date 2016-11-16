package com.luv2code.springdemo;

public class HurlingCoach implements Coach {
	
private FortuneService fortuneService;
	
	public HurlingCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Puck the ball around for 25 minutes";
	}

	@Override
	public String getDailyFortune() {
		return "Horse into it! " + fortuneService.getFortune();
	}

}
