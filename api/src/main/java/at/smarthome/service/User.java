package at.smarthome.service;

public class User {
    private String name;
    public String getName() {
        return name;
    }

    private Integer id = 0;
    public Integer getId() {
        return id;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
