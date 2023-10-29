package entities.request;

public interface RequestHandler<T> {
    void handleRequest(Request<T> request);
    void setNextHandler(RequestHandler<T> nextHandler);
}
