package com.microservices.guesswhoservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import com.microservices.guesswhoservice.bean.ResultResponse;
import com.microservices.guesswhoservice.bean.ResultTable;
import com.microservices.guesswhoservice.repository.UserRepository;

@RestController
@Transactional
public class GuessWhoController {

	public List<Questions> questions;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepo;

	public void retrieveQuestions() {
		questions = userRepo.retrieveQuestions();
	}

	public List<Questions> startGame() {
		shuffleAllTheContentsInTheList();
		ArrayList<Questions> listOf10Questions = new ArrayList<Questions>();
		for (int i = 0; i < 5; i++) {
			Questions original = selectRandomWord(i);
			String shuffled = getShuffledWord(original.getAnswer());
			original.setShuffledWord(shuffled);
			listOf10Questions.add(original);
		}
		return listOf10Questions;
	}

	@PostMapping("/guess-who/GetQuestionsRequest")
	public List<Questions> returnListOfTenQuestions(@RequestBody GetQuestionsRequest req) {
		AuthorizeUserResponse callDotNetService = callDotNetService(req.getEmailID(), req.getToken());
		userRepo.addEntriesToUserTable(callDotNetService.getUser().getEmailID(), callDotNetService.getUser().getToken(),
				callDotNetService.getUser().getUserID());
		retrieveQuestions();
		List<Questions> list = startGame();
		logger.info("{}", list);
		return list;
	}

	@PostMapping("/guess-who/GetResults")
	public ResultResponse updateAnswers(@RequestBody List<ResultTable> req) {
		ResultResponse response = null;
		try {
			for (ResultTable result : req) {
				userRepo.addEntriesToJoinTable(result.getUserId(), result.getQuestionId(), result.getTokenId(),
						result.getAnswerStatus());
				response = new ResultResponse("Data inserted", true);
			}
		} catch (Exception e) {
			response = new ResultResponse("Data not inserted", false);
		}
		return response;
	}

	public AuthorizeUserResponse callDotNetService(String emailId, String tokenId) {
		AuthorizeUserRequest request = new AuthorizeUserRequest(emailId, tokenId);
		//String url = "http://private-1fb86-guesswho1.apiary-mock.com/api/login/auth";
		 String url = "http://guesswhodotnet/api/login/auth";
		// String url = "http://192.168.2.81:30848/api/login/auth";
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
