package com.project.gameCatalog.controllers;

import com.project.gameCatalog.dtos.UserDto;
import com.project.gameCatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto)
    {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto)
    {
        return userService.userLogin(userDto);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
