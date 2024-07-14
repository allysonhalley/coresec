package com.hemti.coresec.controller;

import com.hemti.coresec.model.user.User;
import com.hemti.coresec.model.user.UserRequestLoginDTO;
import com.hemti.coresec.model.user.UserRequestRegisterDTO;
import com.hemti.coresec.model.user.UserResponseLoginDTO;
import com.hemti.coresec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import com.hemti.coresec.service.security.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserRequestLoginDTO userRequestLoginDTO) {
        String username = userRequestLoginDTO.username();
        String password = userRequestLoginDTO.password();
        var usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.createToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserResponseLoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestRegisterDTO userRequestRegisterDTO) {
        if (this.userRepository.findByUsername(userRequestRegisterDTO.username()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestRegisterDTO.password());
        User newUser = new User(userRequestRegisterDTO, encryptedPassword);
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
