package entities.user.admin;

import entities.user.Role;
import entities.user.User;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, Role.ADMIN);
    }
}
