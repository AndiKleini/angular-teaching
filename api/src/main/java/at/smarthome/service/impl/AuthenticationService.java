package at.smarthome.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import at.smarthome.persistence.LoginCredentialsRepository;
import at.smarthome.persistence.entities.LoginCredentials;
import at.smarthome.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

    private UserService userService;
    private LoginCredentialsRepository loginCredentialsRepository;

    @Autowired
    public AuthenticationService(UserService userService, LoginCredentialsRepository loginCredentialsRepository) {
        this.userService = userService;
        this.loginCredentialsRepository = loginCredentialsRepository;
    }

    @Override
    public String issue(Long userId) {
        if (userId == 0L) {
            return null;
        }
        return JWT.create()
            .withSubject(String.valueOf(userId))
            .withExpiresAt(Instant.now().plus(Duration.of(1, java.time.temporal.ChronoUnit.HOURS)))
            .withClaim("a", List.of("ROLE_USER")) 
            .sign(Algorithm.HMAC256("this_is_a_secret_key"));
    }

    @Override
    public Long validate(String token) {
        try {
            JWT.require(Algorithm.HMAC256("this_is_a_secret_key")).build().verify(token);
            return Long.parseLong(JWT.decode(token).getSubject());
        } catch (Exception e) {
            return 0L;
        }
    }

    @Override
    public Long authenticate(String username, String password) {
        String hashedPassword = hashPassword(password);
        var credentialsIterable = loginCredentialsRepository.findAll();
        LoginCredentials credentials = StreamSupport.stream(credentialsIterable.spliterator(), false)
            .filter(c -> c.getUsername().equals(username) && c.getPassword().equals(hashedPassword))
            .findFirst()
            .orElse(null);
        if (credentials == null) {
            return 0L;
        }
        return userService.lookUpUser(username);
    }

    public void createCredentials(String name, String password) {
        this.loginCredentialsRepository.save(new LoginCredentials(name, hashPassword(password)));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }
}
