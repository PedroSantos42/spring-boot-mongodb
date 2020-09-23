package com.pedrosantos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import com.pedrosantos.workshopmongo.domain.Post;
import com.pedrosantos.workshopmongo.repository.PostRepository;
import com.pedrosantos.workshopmongo.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostRepository repo;

    public Post findById(String id)  {
        Optional<Post> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }
}
