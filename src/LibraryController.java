import entities.catalogue.Catalogue;
import entities.catalogue.CatalogueItem;
import entities.catalogue.PurchaseOption;
import entities.catalogue.search.spec.Spec;
import entities.order.Order;
import entities.order.OrderController;
import entities.order.OrderItem;
import entities.payment.PaymentType;
import entities.request.*;
import entities.storage.controller.StorageController;
import entities.user.Role;
import entities.user.User;
import entities.user.buyer.Buyer;
import entities.user.controller.UserController;
import entities.user.seller.InventoryItem;
import entities.user.seller.Seller;
import entities.user.seller.SoldUnit;

import java.util.*;

public class LibraryController {
    private UserController userController;
    private Catalogue catalogue;
    private OrderController orderController;
    private StorageController storageController;
    public LibraryController() {
        userController = UserController.getInstance();
        catalogue = Catalogue.getInstance();
        orderController = OrderController.getInstance();
        storageController = StorageController.getInstance();
        signupAdmin();
    }

    private void signupAdmin()  {
        try {
            userController.signup("admin", "admin", Role.ADMIN);
        } catch (Exception e) {

        }
    }

    public void signup(String username, String password, Role role) throws Exception {
        User user = userController.signup(username, password, role);
        addInventoryObserver(user, catalogue);
    }

    private void addInventoryObserver(User user, Catalogue catalogue) {
        if (user instanceof Seller) {
            Seller seller = (Seller) user;
            seller.addInventoryListener(catalogue);
        }
    }

    public User login(String username, String password) throws Exception {
        return userController.login(username, password);
    }

    public void addBook(InventoryItem book, Seller seller) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.SELLER));
        middleware.doFilter(new Headers(seller.getId(), seller.getRole()));
        seller.addBookToInventory(book);
    }

    public List<CatalogueItem> getBookCatalogue(User user) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.ADMIN, Role.BUYER));
        middleware.doFilter(new Headers(user.getId(), user.getRole()));
        return catalogue.getAllBooks();
    }

    public List<CatalogueItem> searchBooks(User user, Map<String, Spec<?>> spec) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.BUYER));
        middleware.doFilter(new Headers(user.getId(), user.getRole()));

        return catalogue.search(spec);
    }

    public void addBookToCart(Buyer buyer, PurchaseOption purchaseOption) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.BUYER));
        middleware.doFilter(new Headers(buyer.getId(), buyer.getRole()));

        purchaseOption.addToCart(buyer);
    }

    public Order placeOrder(Buyer buyer, PaymentType paymentType) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.BUYER));
        middleware.doFilter(new Headers(buyer.getId(), buyer.getRole()));
        return orderController.createOrder(buyer, paymentType);
    }

    public void payOrder(Buyer buyer, Order order) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.BUYER));
        middleware.doFilter(new Headers(buyer.getId(), buyer.getRole()));
        order.pay();
    }

    public List<SoldUnit> getSoldBooks(Seller seller) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.SELLER));
        middleware.doFilter(new Headers(seller.getId(), seller.getRole()));
        return seller.getSoldBooks();
    }

    public List<OrderItem> getPurchasedBooks(Buyer buyer) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.ADMIN, Role.BUYER));
        middleware.doFilter(new Headers(buyer.getId(), buyer.getRole()));
        return buyer.getPurchasedBooks();
    }

    public String downloadFile(Buyer buyer, String fileId, String sellerId) {
        Middleware middleware = MiddlewareFactory.createMiddlewareChain(Set.of(Role.BUYER));
        middleware.doFilter(new Headers(buyer.getId(), buyer.getRole()));
        return storageController.downloadFile(buyer, fileId + ":" + sellerId);
    }
}
