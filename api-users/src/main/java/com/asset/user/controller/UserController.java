package com.asset.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.asset.user.entity.User;
import com.asset.user.service.UserService;

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Save one User to database",
            description = "Save User, the key in the payload will be used as id. The response is the saved User",
            tags = { "User", "POST" })
    @PostMapping("/users")
    public User SaveUser(@RequestBody User user){
        user.setId(user.getKey());
        return userService.saveEmpDetails(user);
    }

    @Operation(
            summary = "Get one User",
            description = "Use key is required. user is unique",
            tags = { "User", "GET" })
    @GetMapping("/users/{key}")
    public User getBHAProfile(@PathVariable("key") String key){
        return userService.getUser(key);
    }
}
