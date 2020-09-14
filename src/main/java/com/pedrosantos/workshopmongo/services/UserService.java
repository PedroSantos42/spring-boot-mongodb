package com.pedrosantos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import com.pedrosantos.workshopmongo.domain.User;
import com.pedrosantos.workshopmongo.repository.UserRepository;
import com.pedrosantos.workshopmongo.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;

    public User findById(String id)  {
        Optional<User> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}
