package entities.catalogue;

import entities.catalogue.search.BookSpec;
import entities.catalogue.search.spec.AuthorSpec;
import entities.catalogue.search.spec.PublisherSpec;
import entities.catalogue.search.spec.TitleSpec;
import valueobjects.BookId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CatalogueItem {
    private BookId bookId;
    private String title;
    private String author;
    private String publisher;
    private String edition;
    private BookSpec spec;
    private Map<String, PurchaseOption> purchaseOptions;

    private CatalogueItem(Builder builder) {
        title = builder.title;
        author = builder.author;
        publisher = builder.publisher;
        edition = builder.edition;
        bookId = new BookId(title, publisher, edition);
        purchaseOptions = new TreeMap<>();

        spec = new BookSpec(Map.ofEntries(
                Map.entry("title", new TitleSpec(title)),
                Map.entry("publisher", new PublisherSpec(publisher)),
                Map.entry("author", new AuthorSpec(author))
        ));
    }

    public BookSpec getSpec() {
        return spec;
    }

    public List<PurchaseOption> getPurchaseOptions() {
        return purchaseOptions.values()
                .stream()
                .filter(
                        purchaseOption -> !purchaseOption.getSeller().isSuspended()).collect(Collectors.toList()
                );
    }

    public void addPurchaseOption(PurchaseOption purchaseOption) {
        purchaseOptions.put(purchaseOption.getSellerId(), purchaseOption);
    }

    public String getBookId() {
        return bookId.getId();
    }

    public String getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public String getPublisher() {
        return publisher;
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private String title;
        private String author;
        private String publisher;
        private String edition;
        private Map<String, PurchaseOption> purchaseOptions;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder author(String val) {
            author = val;
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

        public Builder purchaseOptions(Map<String, PurchaseOption> val) {
            purchaseOptions = val;
            return this;
        }

        public CatalogueItem build() {
            return new CatalogueItem(this);
        }
    }
}
