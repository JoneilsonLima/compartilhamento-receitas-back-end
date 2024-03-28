package com.receita.controller;

import com.receita.model.User;
import com.receita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createuser(@RequestBody User user) throws Exception{
        User isExist  = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("user is exist with " + user.getEmail());
        }

        User savedUser = userRepository.save(user);

        return savedUser;
    }


}
