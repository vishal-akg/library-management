package entities.catalogue.search.spec;

public class AuthorSpec implements Spec<String>{
    private String author;
    public AuthorSpec(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean matches(Spec<String> other) {
        if (other instanceof AuthorSpec) {
            AuthorSpec otherAuthorSpec = (AuthorSpec) other;
            return author.equalsIgnoreCase(otherAuthorSpec.getAuthor());
        }
        return false;
    }
}
