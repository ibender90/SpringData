package ru.geek.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.api.DTO.AppError;
import ru.geek.market.api.DTO.JwtRequest;
import ru.geek.market.api.DTO.JwtResponse;
import ru.geek.market.api.DTO.RegisterUserDto;
import ru.geek.market.auth.entities.User;
import ru.geek.market.auth.services.JwtService;
import ru.geek.market.auth.services.UserService;


@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @PostMapping("/authenticate")
//    public AuthResponse token(@RequestBody AuthRequest request) {
//        Authentication authenticate = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        UserDetails user = (UserDetails) authenticate.getPrincipal();
//        String token = jwtService.generateToken(user);
//
//        return new AuthResponse(token);
//    }

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto){

        if(!registerUserDto.getPass().equals(registerUserDto.getConfirmPass())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Passwords mismatch"), HttpStatus.BAD_REQUEST);
        }
        if(userService.findByUsername(registerUserDto.getUserName()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "This login is already in use"), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerUserDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPass()));
        userService.createUser(user);

        UserDetails userDetails = userService.loadUserByUsername(registerUserDto.getUserName());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
