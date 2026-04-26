package at.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.smarthome.service.IUserService;
import at.smarthome.service.User;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService  userService) {
        this.userService = userService; 
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(auth.getName());
        log.info("UserController: getUser called for userId " + userId + " and requested id ");
        User user = userService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User name) {
        return this.userService.createUser(name);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(
        @RequestParam(value = "id", defaultValue = "0") Long id) {
        boolean success = this.userService.deleteUser(id);
        if (success) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
