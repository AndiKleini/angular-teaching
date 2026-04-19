package at.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    List<User> users = new ArrayList<>();
    
    public User getUser(Integer id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User createUser(User user) {
        User tmpUser = new User(users.size(), user.getName());
        users.add(tmpUser);
        return tmpUser;
    }

    public boolean deleteUser(Integer id) {
       return users.remove(users.stream().filter(u -> u.getId() == id).findFirst().orElse(null));
    }
}
