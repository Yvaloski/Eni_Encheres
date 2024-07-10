package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.UserService;
import fr.eni.encheres.bo.LoginDto;
import fr.eni.encheres.bo.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

     private final AuthenticationManager authenticationManager;
     UserService userService;
     PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginDto> login() {
        LoginDto loginDto = new LoginDto();
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User logged in successfully!.", HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<User> createUser(){
        User user = new User();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){

        // add check for username exists in a DB
        if(userService.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userService.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("An account already exists for this email!", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setCredit(100);

        userService.addUser(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
