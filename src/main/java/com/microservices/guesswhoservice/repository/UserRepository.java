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
import com.microservices.guesswhoservice.bean.Result;
import com.microservices.guesswhoservice.bean.User;

@Repository
public class UserRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	EntityManager em;

	@Transactional
	public void addEntriesToUserTable(String emailId, String tokenId, int userId) {
		User user = new User(emailId, tokenId, userId);
		//Query query = em.createQuery("SELECT COUNT(u.id) FROM User u WHERE u.emailId=:emailId");
		//query.setParameter("emailId", emailId);
		//Long count = (Long) query.getSingleResult();
		//if (count == 0) {
			em.persist(user);
		//}
	}

	@Transactional
	public void addEntriesToJoinTable(int userId, int questionId, String tokenId, boolean answerStatus) {
		Result result = new Result();
		result.setUserId(userId);
		result.setQuestionId(questionId);
		result.setTokenId(tokenId);
		result.setAnswerStatus(answerStatus);
		em.persist(result);
	}

	public List<Questions> retrieveQuestions() {

		Query query = em.createNamedQuery("query_get_all_questions");
		List<Questions> resultList = query.getResultList();
		return resultList;
	}
}
