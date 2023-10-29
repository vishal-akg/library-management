package entities.user.seller;

import entities.catalogue.Catalogue;
import entities.catalogue.CatalogueItem;
import entities.user.Role;
import entities.user.User;
import entities.user.seller.inventory.Inventory;
import valueobjects.BookId;

import java.util.List;

public class Seller extends User {
    private Inventory inventory;
    private boolean isSuspended;
    private SellingTimeline sellingTimeline;

    public Seller(String username, String password) {
        super(username, password, Role.SELLER);
        inventory = new Inventory(this);
        sellingTimeline = new SellingTimeline();
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public void addInventoryListener(Catalogue catalogue) {
        inventory.addObserver(catalogue);
    }

    public void addBookToInventory(InventoryItem book) {
        inventory.addBook(book);
    }

    public void addSoldBook(String title, Double price) {
        sellingTimeline.addBook(title, price);
    }

    public List<SoldUnit> getSoldBooks() {
        return sellingTimeline.getSoldUnits();
    }
}
