package com.estech.MatchesApp.controller;

import com.estech.MatchesApp.model.User;
import com.estech.MatchesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> userList() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User recibedUser) {
        return userService.login(recibedUser);
    }

}
