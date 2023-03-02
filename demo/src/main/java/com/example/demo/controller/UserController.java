package com.example.demo.controller;

import com.example.demo.datacreation.UserData;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserData userData;


    @GetMapping
    public List getAllUsers() {
        String test = "test";
        return userData.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        String test = "test";
        return userData.getUserById(userId);
    }

    @GetMapping("/event/{eventType}")
    public List<User> getUserById(@PathVariable String eventType) {
        String test = "test";
        return userData.getUsersByEventType(eventType);
    }
}
