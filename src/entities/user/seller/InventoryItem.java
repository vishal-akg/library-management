package entities.user.seller;

import valueobjects.BookId;

import java.util.List;

public class InventoryItem {
    private BookId bookId;
    private String title;
    private String publisher;
    private String edition;
    private String author;
    private Double price;
    private String content;

    private InventoryItem(Builder builder) {
        title = builder.title;
        publisher = builder.publisher;
        edition = builder.edition;
        author = builder.author;
        price = builder.price;
        content = builder.content;
        bookId = new BookId(builder.title, builder.publisher, builder.edition);
    }

    public BookId getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getEdition() {
        return edition;
    }

    public String getAuthor() {
        return author;
    }

    public Double getPrice() {
        return price;
    }

    public String getContent() {
        return content;
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private String title;
        private String publisher;
        private String edition;
        private String author;
        private Double price;
        private String content;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder publisher(String val) {
            publisher = val;
            return this;
        }

        public Builder edition(String val) {
            edition = val;
            return this;
        }

        public Builder author(String val) {
            author = val;
            return this;
        }

        public Builder price(Double val) {
            price = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public InventoryItem build() {
            return new InventoryItem(this);
        }
    }
}
