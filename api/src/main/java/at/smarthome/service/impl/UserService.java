package at.smarthome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import at.smarthome.service.IUserService;
import at.smarthome.service.User;

@Service
public class UserService implements IUserService {

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
    public Long lookUpUser(String username, String password) {
        // In a real application, you would look up the user in the database and check  the password hash. Here we just check if the username is "user" and the password is "password".
        User tmp = users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
        return tmp != null ? tmp.getId() : 0L;
    }
}
