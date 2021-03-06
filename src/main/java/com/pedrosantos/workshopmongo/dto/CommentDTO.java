package com.pedrosantos.workshopmongo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private LocalDate date;
    private AuthorDTO author;

    public CommentDTO(String text, LocalDate date, AuthorDTO author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }
}
