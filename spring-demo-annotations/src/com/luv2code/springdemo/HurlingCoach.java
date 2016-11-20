package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class HurlingCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Run around the field, then have a game of backs and forwards";
	}

	@Override
	public String getDailyFortune() {
		return null;
	}

}
