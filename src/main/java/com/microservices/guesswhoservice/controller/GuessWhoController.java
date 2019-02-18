package com.microservices.guesswhoservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.guesswhoservice.bean.Questions;
import com.microservices.guesswhoservice.repository.UserRepository;

@RestController
@Transactional
public class GuessWhoController {

	public List<Questions> questions;
	ArrayList<Questions> listOf10Questions = new ArrayList<Questions>();

	/*@Autowired
	private CategoryRepository categoryRepo;*/
	
	@Autowired
	private UserRepository userRepo;

	//@GetMapping("/guess-who/category/{category}")
	public void retrieveQuestions() {
		//questions = categoryRepo.retrieveQuestionsForCategory();
		questions = userRepo.retrieveQuestionsForCategory();
		//startGame();
	}

	public List<Questions> startGame() {
		shuffleAllTheContentsInTheList();
		for (int i = 0; i <= 10; i++) {
			Questions original = selectRandomWord(i);
			String shuffled = getShuffledWord(original.getAnswer());
			original.setShuffledWord(shuffled);
			listOf10Questions.add(original);
			//System.out.println("Shuffled word is: " + shuffled);
			/*String userGuess = getUserGuess();
			if (original.getAnswer().equalsIgnoreCase(userGuess)) {
				correctAnswers++;
				System.out.println("Congratulations! Your Answer is correct");
			} else {
				System.out.println("Sorry, Wrong answer");
			}*/
		}

		//System.out.println("Your correct Answers :" + correctAnswers);
		System.out.println("Your question list:" +listOf10Questions);
		//System.out.println("Please wait for another chance !!");
		return listOf10Questions;
	}
	
	
/*	public void addListOfQuestionsToJoinTable() {
		userRepo.addEntriesToUserTable("shefaligupta1234567@gmail.com", "mhj-665gvh");
		int userId = userRepo.retrieveUserIdFromEmail("shefaligupta1234567@gmail.com");
		retrieveQuestions("bollywood");
		List<Questions> list = startGame();
		userRepo.addEntriesToJoinTable(list, userId);
	}*/
	
	@GetMapping("/guess-who/emailId/{emailId}/testTokenId/{testTokenId}")
	public List<Questions> addListOfQuestionsToJoinTable(@PathVariable String emailId, @PathVariable String testTokenId) {
		userRepo.addEntriesToUserTable(emailId, testTokenId);
		int userId = userRepo.retrieveUserIdFromEmail(emailId);
		retrieveQuestions();
		List<Questions> list = startGame();
		userRepo.addEntriesToJoinTable(list, userId);
		return list;
	}

	public String getUserGuess() {
		Scanner sn = new Scanner(System.in);
		System.out.println("Please type in the original word: ");
		return sn.nextLine();
	}

	public List<Questions> shuffleAllTheContentsInTheList() {
		Collections.shuffle(questions);
		return questions;
	}

	public Questions selectRandomWord(int current) {
		Questions randomCandidate;
		randomCandidate = questions.get(current);
		return randomCandidate;
	}

	public String getShuffledWord(String wordToBeShuffled) {
		String shuffledWord = wordToBeShuffled;
		int wordSize = wordToBeShuffled.length();
		int shuffleCount = 10; // randomly shuffle letters 10 times
		for (int i = 0; i < shuffleCount; i++) {
			// swap letters in two indexes
			int position1 = ThreadLocalRandom.current().nextInt(0, wordSize - 1);
			int position2 = ThreadLocalRandom.current().nextInt(0, wordSize - 1);
			shuffledWord = swapCharacters(shuffledWord, position1, position2);
		}
		return shuffledWord;
	}

	private String swapCharacters(String shuffledWord, int position1, int position2) {
		String removeSpaceFromShuffledWord = shuffledWord.replaceAll("\\s", "");
		char[] charArray = removeSpaceFromShuffledWord.toCharArray();
		char temp = charArray[position1];
		charArray[position1] = charArray[position2];
		charArray[position2] = temp;
		return new String(charArray);
	}

}
