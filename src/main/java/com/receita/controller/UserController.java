package com.receita.controller;

import com.receita.model.User;
import com.receita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createuser(@RequestBody User user) throws Exception{
        User isExist  = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("user is exist with " + user.getEmail());
        }

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(value = "userId") Long userId) throws Exception {
        userRepository.deleteById(userId);

        return "User deleted sucessfully";
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }
}
