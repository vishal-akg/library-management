package entities.user.seller.inventory.observer;

import entities.catalogue.CatalogueItem;
import entities.user.seller.InventoryItem;

public interface InventoryObservable {
    void addObserver(InventoryObserver observer);
    void removeObserver(InventoryObserver observer);
    void notifyObservers(InventoryItem book);
}
