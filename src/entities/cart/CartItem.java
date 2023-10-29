package entities.cart;

import entities.catalogue.CatalogueItem;
import entities.user.seller.Seller;

public class CartItem {
    private CatalogueItem book;
    private Seller seller;
    private Double unitPrice;

    public CartItem(CatalogueItem book, Seller seller, Double unitPrice) {
        this.book = book;
        this.seller = seller;
        this.unitPrice = unitPrice;
    }

    public CatalogueItem getBook() {
        return book;
    }

    public Seller getSeller() {
        return seller;
    }

    public Double getPrice() {
        return unitPrice;
    }
}
