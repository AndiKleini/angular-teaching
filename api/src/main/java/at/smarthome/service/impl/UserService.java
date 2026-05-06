package at.smarthome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.smarthome.persistence.LoginCredentialsRepository;
import at.smarthome.service.IUserService;
import at.smarthome.service.User;

@Service
public class UserService implements IUserService {

    private final LoginCredentialsRepository credentialsRepository;

    @Autowired
    public UserService(LoginCredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    List<User> users = new ArrayList<>() {
        {
            add(new User(1L, "Teacher"));
        }
    };

    @Override
    public User getUser(Long id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User createUser(User user) {
        User tmpUser = new User(Long.valueOf(users.size() + 1), user.getName());
        users.add(tmpUser);
        return tmpUser;
    }

    @Override
    public boolean deleteUser(Long id) {
       return users.remove(users.stream().filter(u -> u.getId() == id).findFirst().orElse(null));
    }

    @Override
    public Long lookUpUser(String username) {
       User tmp = users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
        return tmp != null ? tmp.getId() : 0L;
    }

    @Override
    public List<User> searchByUsername(String usernameSubstring) {
        return credentialsRepository.findByUsernameContainingIgnoreCase(usernameSubstring)
            .stream()
            .map(c -> new User(lookUpUser(c.getUsername()), c.getUsername()))
            .toList();
    }

    @Override
    public List<User> searchByIdGreaterThan(Long threshold) {
        return credentialsRepository.findByIdGreaterThan(threshold)
            .stream()
            .map(c -> new User(lookUpUser(c.getUsername()), c.getUsername()))
            .toList();
    }
}
