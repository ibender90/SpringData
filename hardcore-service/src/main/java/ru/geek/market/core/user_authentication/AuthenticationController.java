package ru.geek.market.core.user_authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.api.DTO.JwtRequest;
import ru.geek.market.api.DTO.JwtResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ){
//        return ResponseEntity.ok(service.register(request));
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(
            @RequestBody JwtRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
