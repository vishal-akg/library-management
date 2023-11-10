package entities.request;

public interface Middleware {
    void doFilter(Headers headers);
}
