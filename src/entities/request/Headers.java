package entities.request;

import entities.user.Role;

public class Headers {
    private String token;
    private Role role;

    public Headers(String token, Role role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }
}
