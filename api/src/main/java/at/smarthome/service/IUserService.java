package at.smarthome.service;

public interface IUserService {

    User getUser(Long id);

    User createUser(User user);

    boolean deleteUser(Long id);

    Long lookUpUser(String username);

}