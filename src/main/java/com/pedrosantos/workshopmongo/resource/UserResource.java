package com.pedrosantos.workshopmongo.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pedrosantos.workshopmongo.domain.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User ana = new User("1", "Ana Maria", "maria@email.com");
        User bruno = new User("2", "Bruno Silva", "bruno@email.com");

        List<User> list = new ArrayList<>();

        list.addAll(Arrays.asList(ana, bruno));

        return ResponseEntity.ok().body(list);
    }
}
