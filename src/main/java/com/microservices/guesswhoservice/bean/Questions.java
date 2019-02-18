package com.microservices.guesswhoservice.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Questions")
@Table(name = "T_QUESTIONS")
@NamedQuery(name="query_get_all_questions", query="SELECT q FROM Questions q")
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QuestionID")
	private int id;

	@Column(name = "Title")
	private String title;

	@Column(name = "ImagePath")
	private String imagePath;

	@Column(name = "AnswerText")
	private String answer;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "CreatedDate")
	@JsonIgnore
	private Date createdDate;

	@Column(name = "ModifiedDate")
	@JsonIgnore
	private Date modifiedDate;

//	@ManyToOne
//	@JsonIgnore
//	private Categories category;

	@OneToMany(mappedBy = "primaryKey.questions", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UserQuestion> userQuestion = new HashSet<UserQuestion>();

	@Transient
	private String shuffledWord;

	protected Questions() {
	}

	public Questions(String title, String imagePath, String answer, boolean isActive, Date createdDate,
			Date modifiedDate) {
		this.title = title;
		this.imagePath = imagePath;
		this.answer = answer;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
/*
	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}*/

	public String getShuffledWord() {
		return shuffledWord;
	}

	public void setShuffledWord(String shuffledWord) {
		this.shuffledWord = shuffledWord;
	}

	public void addUserQuestion(UserQuestion userQuestion) {
		this.userQuestion.add(userQuestion);
	}

	public Set<UserQuestion> getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(Set<UserQuestion> userQuestion) {
		this.userQuestion = userQuestion;
	}

	@Override
	public String toString() {
		return "Questions [title=" + title + ", imagePath=" + imagePath + ", answer=" + answer + ", isActive="
				+ isActive + ", shuffledWord="
				+ shuffledWord + "]";
	}
}
