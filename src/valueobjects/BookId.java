package valueobjects;

import java.util.Objects;

public class BookId implements Comparable{
    private String id;

    public BookId(String title, String publisher, String edition) {
        this.id = String.format("%s:%s:%s", title,publisher,edition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return Objects.equals(id, bookId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        return equals(o) ? 0 : 1;
    }
}
