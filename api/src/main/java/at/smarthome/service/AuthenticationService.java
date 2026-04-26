package at.smarthome.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthenticationService {

    public String issue(long userId) {
        return JWT.create()
            .withSubject(String.valueOf(userId))
            .withExpiresAt(Instant.now().plus(Duration.of(1, java.time.temporal.ChronoUnit.HOURS)))
            .withClaim("a", List.of("ROLE_USER")) 
            .sign(Algorithm.HMAC256("this_is_a_secret_key"));
    }

    public boolean validate(String token) {
        try {
            JWT.require(Algorithm.HMAC256("this_is_a_secret_key")).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long authenticate(String username, String password) {
        return 1227;
    }
}
