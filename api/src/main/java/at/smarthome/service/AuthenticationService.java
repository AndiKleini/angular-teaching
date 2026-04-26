package at.smarthome.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthenticationService {

    private UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public String issue(long userId) {
        if (userId == 0L) {
            return null;
        }
        return JWT.create()
            .withSubject(String.valueOf(userId))
            .withExpiresAt(Instant.now().plus(Duration.of(1, java.time.temporal.ChronoUnit.HOURS)))
            .withClaim("a", List.of("ROLE_USER")) 
            .sign(Algorithm.HMAC256("this_is_a_secret_key"));
    }

    public Long validate(String token) {
        try {
            JWT.require(Algorithm.HMAC256("this_is_a_secret_key")).build().verify(token);
            return Long.parseLong(JWT.decode(token).getSubject());
        } catch (Exception e) {
            return 0L;
        }
    }

    public long authenticate(String username, String password) {
        return userService.lookUpUser(username, password);
    }
}
