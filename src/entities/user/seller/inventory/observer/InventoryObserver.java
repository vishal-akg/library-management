package entities.user.seller.inventory.observer;

import entities.catalogue.CatalogueItem;
import entities.user.seller.InventoryItem;
import entities.user.seller.Seller;

public interface InventoryObserver {
    void update(InventoryItem inventoryItem, Seller seller);
}
