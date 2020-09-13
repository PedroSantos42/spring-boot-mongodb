package com.pedrosantos.workshopmongo.services;

import java.util.List;

import com.pedrosantos.workshopmongo.domain.User;
import com.pedrosantos.workshopmongo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }
}
