package entities.request;

import entities.user.Role;

import java.util.Set;

public class AuthorizationMiddleware extends BaseMiddleware{
    private Set<Role> anyRole;

    public AuthorizationMiddleware(Set<Role> anyRole) {
        this.anyRole = anyRole;
    }

    @Override
    public void doFilter(Headers headers) {
        if (headers.getRole() == null || !anyRole.contains(headers.getRole())) {
            throw new SecurityException("You are not authorized to perform this action");
        }
        super.doFilter(headers);
    }
}
