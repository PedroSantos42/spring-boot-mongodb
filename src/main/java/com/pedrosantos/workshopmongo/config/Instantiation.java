package com.pedrosantos.workshopmongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pedrosantos.workshopmongo.domain.Post;
import com.pedrosantos.workshopmongo.domain.User;
import com.pedrosantos.workshopmongo.dto.AuthorDTO;
import com.pedrosantos.workshopmongo.dto.CommentDTO;
import com.pedrosantos.workshopmongo.repository.PostRepository;
import com.pedrosantos.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        
        Post post1 = new Post(null, LocalDate.of(2018, 3, 21), "Partiu viagem!", "Vou viajar para São Paulo, abraços...", new AuthorDTO(maria));
        Post post2 = new Post(null, LocalDate.of(2018, 3, 23), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));
        
        CommentDTO comment1 = new CommentDTO("Boa viagem, mano!", LocalDate.of(2018, 3, 21), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite!", LocalDate.of(2018, 3, 22), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", LocalDate.of(2018, 3, 23), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

		userRepository.save(maria);
	}
}
