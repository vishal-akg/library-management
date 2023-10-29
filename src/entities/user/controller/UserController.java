package entities.user.controller;

import entities.user.Role;
import entities.user.User;
import entities.user.factory.UserFactory;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private Map<String, User> users;
    private static UserController instance;
    private UserController() {
        users = new HashMap<>();
    }
    public static UserController getInstance() {
        if (instance == null) {
            synchronized (UserController.class) {
                if (instance == null) {
                    instance = new UserController();
                }
            }
        }
        return instance;
    }

    public User signup(String username, String password, Role role) throws Exception {
        if (users.containsKey(username)) {
            throw new Exception("User already exist");
        }
        User user = UserFactory.createUser(role, username, password);
        users.put(username, user);
        return user;
    }

    public User login(String username, String password) throws Exception {
        User user = users.get(username);
        System.out.println(users);
        if (user == null || user.getPassword() != password) {
            throw new Exception("Bad Credentials");
        }
        return user;
    }
}
