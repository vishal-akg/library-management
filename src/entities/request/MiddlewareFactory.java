package entities.request;

import entities.user.Role;

import java.util.Set;

public class MiddlewareFactory {
    public static Middleware createMiddlewareChain(Set<Role> roles) {
        AuthenticationMiddleware authenticationMiddleware = new AuthenticationMiddleware();
        AuthorizationMiddleware authorizationMiddleware = new AuthorizationMiddleware(roles);
        authorizationMiddleware.setNextMiddleware(authorizationMiddleware);
        return authenticationMiddleware;
    }
}
