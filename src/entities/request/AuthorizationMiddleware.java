package entities.request;

import entities.user.Role;

import java.util.Set;

public class AuthorizationMiddleware<T> implements RequestHandler<T>{
    private RequestHandler<T> nextHandler;
    private Set<Role> anyRole;

    public AuthorizationMiddleware(RequestHandler<T> nextHandler, Set<Role> anyRole) {
        this.nextHandler = nextHandler;
        this.anyRole = anyRole;
    }

    @Override
    public void handleRequest(Request<T> request) {
        if (authorized(request)) {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }

    private boolean authorized(Request<T> request) {
        return anyRole.contains(request.getUser().getRole());
    }

    @Override
    public void setNextHandler(RequestHandler<T> nextHandler) {
        this.nextHandler = nextHandler;
    }
}
