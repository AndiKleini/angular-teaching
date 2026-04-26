package at.smarthome.service;

public interface IAuthenticationService {

    String issue(Long userId);

    Long validate(String token);

    Long authenticate(String username, String password);
}