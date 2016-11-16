package com.luv2code.springdemo;

import java.util.Random;

public class GoodFortuneService implements FortuneService {

	String[] fortunes = {"Fortune 1", "Fortune 2", "Fortune 3"};
	
	@Override
	public String getFortune() {
		Random random = new Random();
		return fortunes[random.nextInt(3)];
	}

}
