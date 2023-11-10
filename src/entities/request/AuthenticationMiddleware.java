package entities.request;

public class AuthenticationMiddleware extends BaseMiddleware{

    @Override
    public void doFilter(Headers headers) {
        if (headers.getToken() == null) {
            throw new SecurityException("You are not authenticated");
        }
        super.doFilter(headers);
    }
}
