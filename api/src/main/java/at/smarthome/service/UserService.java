package at.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    List<User> users = new ArrayList<>() {
        {
            add(new User(1, "Teacher"));
        }
    };
    
    public User getUser(Integer id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User createUser(User user) {
        User tmpUser = new User(users.size() + 1, user.getName());
        users.add(tmpUser);
        return tmpUser;
    }

    public boolean deleteUser(Integer id) {
       return users.remove(users.stream().filter(u -> u.getId() == id).findFirst().orElse(null));
    }

    public long lookUpUser(String username, String password) {
        // In a real application, you would look up the user in the database and check  the password hash. Here we just check if the username is "user" and the password is "password".
        User tmp = users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
        return tmp != null ? tmp.getId() : 0L;
    }
}
