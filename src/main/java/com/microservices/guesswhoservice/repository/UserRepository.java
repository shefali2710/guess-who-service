package com.microservices.guesswhoservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservices.guesswhoservice.bean.Questions;
import com.microservices.guesswhoservice.bean.User;
import com.microservices.guesswhoservice.bean.UserQuestion;
import com.microservices.guesswhoservice.bean.UserQuestionAnswer;

@Repository
public class UserRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	EntityManager em;

	@Transactional
	public int retrieveUserIdFromEmail(String emailId) {
		List<User> user = null;
		int userId = 0;
		Query query = em.createQuery("SELECT u FROM User u WHERE u.emailId=:emailId");
		query.setParameter("emailId", emailId);
		try {
			user = query.getResultList();
			for (User currentUser : user) {
				userId= currentUser.getId();
			}
		} catch (Exception e) {
			// Handle exception
		}
		return  userId;
	}

	@Transactional
	public void addEntriesToUserTable(String emailId, String tokenId, int userId) {
		User user = new User(emailId, tokenId, userId);
		//user.setId(123);
		em.persist(user);
	}

/*	@Transactional
	public void addEntriesToJoinTable(List<Questions> list, int id) {
		for (Questions temp : list) {
			Questions question = em.find(Questions.class, temp.getId());
			User user = em.find(User.class, id);
			UserQuestion userQuestion = new UserQuestion();
			userQuestion.setQuestion(question);
			userQuestion.setUser(user);
			userQuestion.setAnswerStatus(true);
			userQuestion.setTokenId(user.getTestTokenId());
			user.addUserQuestiom(userQuestion);
			em.persist(user);
		}
	}*/
	
/*	@Transactional
	public void addEntriesToJoinTable(int userId, int questionId) {
		for (Questions temp : list) {
			Questions question = em.find(Questions.class, temp.getId());
			User user = em.find(User.class, id);
			UserQuestion userQuestion = new UserQuestion();
			userQuestion.setQuestion(question);
			userQuestion.setUser(user);
			userQuestion.setAnswerStatus(true);
			userQuestion.setTokenId(user.getTestTokenId());
			user.addUserQuestiom(userQuestion);
			em.persist(user);
		}
	}*/

	public List<Questions> retrieveQuestions() {
		
		Query query = em.createNamedQuery("query_get_all_questions");
		List<Questions> resultList = query.getResultList();
		return resultList;
	}

	//public void addEntriesToResultTable(int userId, String tokenId, List<Integer> questionId, boolean answerStatus) {
	//	UserQuestionAnswer result = new UserQuestionAnswer(userId, tokenId, questionId, answerStatus)	;	
	//	em.persist(result);
//	}
}
