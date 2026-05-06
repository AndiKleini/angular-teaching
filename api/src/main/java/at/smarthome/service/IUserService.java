package at.smarthome.service;

import java.util.List;

public interface IUserService {

    User getUser(Long id);

    User createUser(User user);

    boolean deleteUser(Long id);

    Long lookUpUser(String username);

    List<User> searchByUsername(String usernameSubstring);

    List<User> searchByIdGreaterThan(Long threshold);

}