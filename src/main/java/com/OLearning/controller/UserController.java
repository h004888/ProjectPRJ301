package com.OLearning.controller;

import com.OLearning.dto.UserDTO;
import com.OLearning.entity.User;
import com.OLearning.service.UserService;
import com.OLearning.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
     private final UserService userService;
        public UserController(UserService userService) {
        this.userService = userService;
    }
        @GetMapping
         public List<UserDTO> getAllUsers() {
            List<UserDTO> users = userService.getAllUsers();
            return users;
         }
}
