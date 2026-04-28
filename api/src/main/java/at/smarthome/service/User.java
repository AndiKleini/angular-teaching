package at.smarthome.service;

public class User {

    private String name;
    public String getName() {
        return name;
    }

    private String password;
    public String getPassword() {
        return password;
    }

    private Long id = 0L;
    public Long getId() {
        return id;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
