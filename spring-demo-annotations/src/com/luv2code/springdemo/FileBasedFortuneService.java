package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class FileBasedFortuneService implements FortuneService {
	
	private Random random = new Random();
	private List<String> fortunes;
	
	@Override
	public String getFortune() {
		String fortune = null;

		if (fortunes != null && fortunes.size() > 0) {
			int index = random.nextInt(fortunes.size());
			fortune = fortunes.get(index);
		}
		
		return fortune;
	}
	
	@PostConstruct
	public void getFortunesFromFile() {
		System.out.println(">> FileBasedFortuneService inside getFortunesFromFile()");
		try {
			fortunes = new ArrayList<>();
			InputStream inputStream = this.getClass().getResourceAsStream("/fortunesFile.txt");
			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
				String line = bufferedReader.readLine();
				while (line != null) {
					fortunes.add(line);
					line = bufferedReader.readLine();
				}
			} else {
				System.out.println("Sorry, could not find a fortunesFile.txt");
			}
		} catch(IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Sorry, unable to get fortunes");
		}
	}

}
