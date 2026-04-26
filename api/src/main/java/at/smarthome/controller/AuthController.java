package at.smarthome.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import at.smarthome.service.LoginRequest;
import at.smarthome.service.LoginResponse;

@RestController 
public class AuthController {
    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        return new ResponseEntity<LoginResponse>(new LoginResponse("blabla"), HttpStatus.OK);
    }
}