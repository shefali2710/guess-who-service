package com.microservices.guesswhoservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.microservices.guesswhoservice.bean.AuthorizeUserRequest;
import com.microservices.guesswhoservice.bean.AuthorizeUserResponse;
import com.microservices.guesswhoservice.bean.GetQuestionsRequest;
import com.microservices.guesswhoservice.bean.Questions;
import com.microservices.guesswhoservice.bean.ResultTable;
import com.microservices.guesswhoservice.repository.UserRepository;

@RestController
@Transactional
public class GuessWhoController {

	public List<Questions> questions;
	ArrayList<Questions> listOf10Questions = new ArrayList<Questions>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * @GetMapping("/guess-who") public void retrieveQuestions() { //questions =
	 * categoryRepo.retrieveQuestionsForCategory(); //questions =
	 * userRepo.retrieveQuestions(); //startGame(); System.out.println("home page");
	 * logger.info("home page"); }
	 */

	@Autowired
	private UserRepository userRepo;

	// @GetMapping("/guess-who/category/{category}")
	public void retrieveQuestions() {
		// questions = categoryRepo.retrieveQuestionsForCategory();
		questions = userRepo.retrieveQuestions();
		// startGame();
	}

	public List<Questions> startGame() {
		shuffleAllTheContentsInTheList();
		for (int i = 0; i <= 10; i++) {
			Questions original = selectRandomWord(i);
			String shuffled = getShuffledWord(original.getAnswer());
			original.setShuffledWord(shuffled);
			listOf10Questions.add(original);
			// System.out.println("Shuffled word is: " + shuffled);
			/*
			 * String userGuess = getUserGuess(); if
			 * (original.getAnswer().equalsIgnoreCase(userGuess)) { correctAnswers++;
			 * System.out.println("Congratulations! Your Answer is correct"); } else {
			 * System.out.println("Sorry, Wrong answer"); }
			 */
		}

		// System.out.println("Your correct Answers :" + correctAnswers);
		System.out.println("Your question list:" + listOf10Questions);
		// System.out.println("Please wait for another chance !!");
		return listOf10Questions;
	}

	/*
	 * public void addListOfQuestionsToJoinTable() {
	 * userRepo.addEntriesToUserTable("shefaligupta1234567@gmail.com",
	 * "mhj-665gvh"); int userId =
	 * userRepo.retrieveUserIdFromEmail("shefaligupta1234567@gmail.com");
	 * retrieveQuestions("bollywood"); List<Questions> list = startGame();
	 * userRepo.addEntriesToJoinTable(list, userId); }
	 */

	// @PostMapping("/guess-who/GetQuestionsRequest")
	// public void addListOfQuestionsToJoinTable(@RequestBody UserQuestionAnswer
	// req) {
	// userRepo.addEntriesToResultTable(req.getUserId(),req.getTokenId(),req.getQuestionId(),req.getAnswerStatus())
	// ;
	// }

	@PostMapping("/guess-who/GetQuestionsRequest")
	public List<Questions> returnListOfTenQuestions(@RequestBody GetQuestionsRequest req) {
		AuthorizeUserResponse callDotNetService = callDotNetService(req.getEmailId(), req.getTestTokenId());
		userRepo.addEntriesToUserTable(callDotNetService.getUser().getEmailID(), callDotNetService.getUser().getToken(),
				callDotNetService.getUser().getUserID());
		retrieveQuestions();
		List<Questions> list = startGame();
		// userRepo.addEntriesToJoinTable(list, userId);
		return list;
	}
	
	@PostMapping("/guess-who/GetResults")
	public ResultTable updateAnswers(@RequestBody ResultTable req) {
		return req;
	}

	/*
	 * @PostMapping("/guess-who/GetUserDetailsFromDotNetService") public
	 * UserRequestRestApi returnUserDetailsFromDotNetService(@RequestBody
	 * UserRequestRestApi req) {
	 * 
	 * return new UserRequestRestApi(req.getUserId(), req.getEmailId(),
	 * req.getModifiedDate(), req.getToken(), req.getUserName()); }
	 */

	/*
	 * @PostMapping("/guess-who/GetUserDetailsFromDotNetService") public
	 * ResponseEntity<UserRequestRestApi>
	 * returnUserDetailsFromDotNetService(@RequestBody UserRequestRestApi2 req) {
	 * 
	 * return new ResponseEntity<UserRequestRestApi>(HttpStatus.CREATED); }
	 */

	public AuthorizeUserResponse callDotNetService(String emailId, String tokenId) {
		AuthorizeUserRequest request = new AuthorizeUserRequest(emailId, tokenId);
		String url = "http://private-1fb86-guesswho1.apiary-mock.com/auth";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
		ResponseEntity<AuthorizeUserResponse> response = restTemplate.postForEntity(url, request,
				AuthorizeUserResponse.class);
		AuthorizeUserResponse result = response.getBody();
		return result;
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
