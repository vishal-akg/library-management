package entities.request;

public interface RequestCommand<T> {
    void execute(Request<T> request);
}
