package entities.request;

public abstract class BaseMiddleware implements Middleware{
    private Middleware nextMiddleware;

    public void setNextMiddleware(Middleware nextMiddleware) {
        this.nextMiddleware = nextMiddleware;
    }
    @Override
    public void doFilter(Headers headers) {
        if (nextMiddleware != null) {
            this.nextMiddleware.doFilter(headers);
        }
    }
}
