package entities.request;

public class AuthenticationMiddleware<T> implements RequestHandler<T>{
    private RequestHandler<T> nextHandler;

    public AuthenticationMiddleware(RequestHandler<T> nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Request<T> request) {
        if (authenticate(request)) {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }

    @Override
    public void setNextHandler(RequestHandler<T> nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean authenticate(Request<T> request) {
        return request.getUser() != null;
    }
}
