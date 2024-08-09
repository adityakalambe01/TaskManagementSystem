package com.gokapture.controller;

import com.gokapture.entity.User;
import com.gokapture.security.JwtTokenProvider;
import com.gokapture.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    static HttpSession httpSession;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        User savedUser = userService.save(user);
        return (savedUser!=null)?ResponseEntity.ok(savedUser):ResponseEntity.status(HttpStatus.FOUND).body("Username Already Exists");
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            httpSession = request.getSession();
            httpSession.setAttribute("username", user.getUsername());
        }
        String token = jwtTokenProvider.createToken(authentication);

        return ResponseEntity.ok(token);
    }
}
