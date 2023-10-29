package entities.user;

import entities.user.seller.Seller;

import java.util.UUID;

public abstract class User {
    protected String id;
    protected String username;
    protected String password;
    protected Role role;
    public User(String username, String password, Role role) {
        id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
