package com.example.library_backend.controller;

import com.example.library_backend.model.User;
import com.example.library_backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library_backend/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(int id) {
        return service.getUserById(id);
    }

    @GetMapping("/loadUsers")
    public void loadUsers() {
        service.loadUsersFromCSV();
    }

}
