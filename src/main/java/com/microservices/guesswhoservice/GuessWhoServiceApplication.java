package com.microservices.guesswhoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class GuessWhoServiceApplication {

	//@Autowired
	//private GuessWhoController guessController;

	public static void main(String[] args) {
		SpringApplication.run(GuessWhoServiceApplication.class, args);

		/*
		 * ApplicationContext applicationContext =
		 * SpringApplication.run(GuessWhoServiceApplication.class, args);
		 * GuessWhoController guess
		 * =applicationContext.getBean(GuessWhoController.class);
		 * guess.retrieveImageName("bollywood");
		 */
	}

	/*@Override
	public void run(String... args) {
		guessController.addListOfQuestionsToJoinTable();
	}*/

}
