package entities.catalogue.search.spec;

public interface Spec<T> {
    boolean matches(Spec<T> other);
}
