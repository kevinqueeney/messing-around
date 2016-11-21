package com.luv2code.springdemo;

public class EasyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Today will be easy!";
	}

}
