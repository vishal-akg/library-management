package entities.user.seller.inventory;

import entities.catalogue.CatalogueItem;
import entities.storage.controller.StorageController;
import entities.user.seller.InventoryItem;
import entities.user.seller.Seller;
import entities.user.seller.inventory.observer.InventoryObservable;
import entities.user.seller.inventory.observer.InventoryObserver;

import java.util.*;

public class Inventory implements InventoryObservable {
    private Seller seller;
    private List<InventoryItem> books;
    private List<InventoryObserver> observers;
    private StorageController storageController;

    public Inventory(Seller seller) {
        books = new LinkedList<>();
        observers = new LinkedList<>();
        storageController = StorageController.getInstance();
        this.seller = seller;
    }

    public void addBook(InventoryItem book) {
        books.add(book);
        storageController.uploadFile(book.getBookId().getId(), seller.getId(), book.getContent() );
        notifyObservers(book);
    }

    @Override
    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(InventoryItem book) {
        for (InventoryObserver observer: observers) {
            observer.update(book, seller);
        }
    }
}
