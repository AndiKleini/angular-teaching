package at.smarthome.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import at.smarthome.service.IAuthenticationService;
import at.smarthome.service.IChuckNorrisService;
import at.smarthome.service.LoginRequest;
import at.smarthome.service.LoginResponse;

@RestController
public class AuthController {

    private IAuthenticationService authService;
    private IChuckNorrisService chuckNorrisService;

    @Autowired
    public AuthController(IAuthenticationService authService, IChuckNorrisService chuckNorrisService) {
        this.authService = authService;
        this.chuckNorrisService = chuckNorrisService;
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        // Kick off the Chuck Norris call before authenticating so it runs in parallel with the DB lookup.
        CompletableFuture<String> factFuture = this.chuckNorrisService.getRandomFact();
        long userId = this.authService.authenticate(request.getUsername(), request.getPassword());
        String token = this.authService.issue(userId);
        if (token == null) {
            return new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<LoginResponse>(new LoginResponse(token, factFuture.join()), HttpStatus.OK);
        }
    }
}