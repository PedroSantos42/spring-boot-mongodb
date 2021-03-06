package com.pedrosantos.workshopmongo.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.pedrosantos.workshopmongo.dto.AuthorDTO;

@DynamoDBDocument
public class Post implements Serializable {
	private static final long serialVersionUID = 7699243201379015041L;

	private String id;
	private LocalDate date;
	private String title;
	private String body;
	private AuthorDTO author;

	@DynamoDBAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@DynamoDBAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DynamoDBAttribute
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@DynamoDBAttribute
	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

}
