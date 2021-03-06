package com.pedrosantos.workshopmongo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pedrosantos.workshopmongo.dto.AuthorDTO;
import com.pedrosantos.workshopmongo.dto.CommentDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@Document
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private String id;
	private LocalDate date;
	private String title;
	private String body;
	private AuthorDTO author;
	private List<CommentDTO> comments = new ArrayList<>();

	public Post(String id, LocalDate date, String title, String body, AuthorDTO author) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}
}
