package com.hemti.coresec.controller;
import com.hemti.coresec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
