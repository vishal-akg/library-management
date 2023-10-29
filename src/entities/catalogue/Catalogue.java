package entities.catalogue;

import entities.catalogue.search.spec.Spec;
import entities.user.seller.InventoryItem;
import entities.user.seller.Seller;
import entities.user.seller.inventory.observer.InventoryObserver;
import valueobjects.BookId;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Catalogue implements InventoryObserver {
    private static Catalogue instance;
    private Map<BookId, CatalogueItem> catalogueItems;

    private Catalogue() {
        catalogueItems = new TreeMap<>();
    }

    public static Catalogue getInstance() {
        if (instance == null) {
            synchronized (Catalogue.class) {
                if (instance == null) {
                    instance = new Catalogue();
                }
            }
        }
        return instance;
    }
    @Override
    public void update(InventoryItem inventoryItem, Seller seller) {
        CatalogueItem existingBook = catalogueItems.get(inventoryItem.getBookId());
        if (existingBook != null) {
            existingBook.addPurchaseOption(
                    PurchaseOption.Builder.newBuilder()
                            .book(existingBook)
                            .seller(seller)
                            .unitPrice(inventoryItem.getPrice())
                            .build()
            );
        } else {
            CatalogueItem book = CatalogueItem.Builder.newBuilder()
                    .title(inventoryItem.getTitle())
                    .author(inventoryItem.getAuthor())
                    .edition(inventoryItem.getEdition())
                    .publisher(inventoryItem.getPublisher())
                    .build();

            catalogueItems.put(
                    inventoryItem.getBookId(),
                    book
            );
            book.addPurchaseOption(
                    PurchaseOption.Builder.newBuilder()
                            .book(book)
                            .unitPrice(inventoryItem.getPrice())
                            .seller(seller)
                            .build()
            );
        }

    }

    public List<CatalogueItem> search(Map<String, Spec<?>> otherSpec) {
        List<CatalogueItem> matchingBooks = new LinkedList<>();
        for (Map.Entry<BookId, CatalogueItem> book: catalogueItems.entrySet()) {
            if (book.getValue().getSpec().matches(otherSpec)) {
                matchingBooks.add(book.getValue());
            }
        }
        return matchingBooks;
    }

    public List<CatalogueItem> getAllBooks() {
        return catalogueItems.values().stream().toList();
    }
}
