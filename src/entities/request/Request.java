package entities.request;

import entities.user.Role;
import entities.user.User;

public class Request<T> {
    private T data;
    private User user;

    public Request(T data, User user) {
        this.data = data;
        this.user = user;
    }

    public T getData() {
        return data;
    }

    public User getUser() {
        return user;
    }
}
