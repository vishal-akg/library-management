package entities.order;

import entities.catalogue.CatalogueItem;
import entities.user.seller.Seller;

public class OrderItem {
    private Seller seller;
    private CatalogueItem book;
    private Double price;

    public OrderItem(Seller seller, CatalogueItem book, Double price) {
        this.book = book;
        this.seller = seller;
        this.price = price;
    }

    public CatalogueItem getBook() {
        return book;
    }

    public Seller getSeller() {
        return seller;
    }

    public Double getPrice() {
        return price;
    }
}
