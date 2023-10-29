package entities.user.buyer;

import entities.cart.Cart;
import entities.cart.CartItem;
import entities.catalogue.CatalogueItem;
import entities.order.OrderItem;
import entities.user.Role;
import entities.user.User;
import entities.user.seller.Seller;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Buyer extends User {
    private Cart cart;
    private List<OrderItem> purchasedBooks;

    public Buyer(String username, String password) {

        super(username, password, Role.BUYER);
        this.cart = new Cart();
        this.purchasedBooks = new LinkedList<>();
    }

    public void addToCart(CatalogueItem book, Seller seller, Double unitPrice) {
        cart.addCartItem(new CartItem(book, seller, unitPrice));
    }

    public Cart getCart() {
        return cart;
    }

    public void addPurchasedBooks(OrderItem orderItem) {
        purchasedBooks.add(orderItem);
    }

    public List<OrderItem> getPurchasedBooks() {
        return purchasedBooks;
    }
}
