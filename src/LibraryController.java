import entities.catalogue.Catalogue;
import entities.catalogue.CatalogueItem;
import entities.catalogue.PurchaseOption;
import entities.catalogue.search.spec.Spec;
import entities.order.Order;
import entities.order.OrderController;
import entities.order.OrderItem;
import entities.payment.PaymentType;
import entities.request.AuthenticationMiddleware;
import entities.request.AuthorizationMiddleware;
import entities.request.Request;
import entities.request.RequestHandler;
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
        new AuthenticationMiddleware<InventoryItem>(
                new AuthorizationMiddleware<>(
                        new RequestHandler<>() {
                            @Override
                            public void handleRequest(Request<InventoryItem> request) {
                                seller.addBookToInventory(request.getData());
                            }
                            @Override
                            public void setNextHandler(RequestHandler<InventoryItem> nextHandler) {}
                        },
                        Set.of(Role.SELLER)
                )
        ).handleRequest(new Request<>(book, seller));
    }

    public List<CatalogueItem> getBookCatalogue(User user) {
        List<CatalogueItem> catalogueItems = new LinkedList<>();

         new AuthenticationMiddleware(new AuthorizationMiddleware(new RequestHandler() {
            @Override
            public void handleRequest(Request request) {
                catalogueItems.addAll(catalogue.getAllBooks());
            }

            @Override
            public void setNextHandler(RequestHandler nextHandler) {

            }
        }, Set.of(Role.ADMIN, Role.BUYER))).handleRequest(new Request(null, user));
         return catalogueItems;
    }

    public List<CatalogueItem> searchBooks(User user, Map<String, Spec<?>> spec) {
        List<CatalogueItem> matchingBooks = new LinkedList<>();
        new AuthenticationMiddleware<Map<String, Spec<?>>>(
                new AuthorizationMiddleware<>(new RequestHandler<>() {
                    @Override
                    public void handleRequest(Request<Map<String, Spec<?>>> request) {
                        matchingBooks.addAll(catalogue.search(request.getData()));
                    }

                    @Override
                    public void setNextHandler(RequestHandler<Map<String, Spec<?>>> nextHandler) {

                    }
                }, Set.of(Role.BUYER))
        ).handleRequest(new Request<>(spec, user));
        return matchingBooks;
    }

    public void addBookToCart(Buyer buyer, PurchaseOption purchaseOption) {
        new AuthenticationMiddleware<PurchaseOption>(
                new AuthorizationMiddleware<>(
                        new RequestHandler<PurchaseOption>() {
            @Override
            public void handleRequest(Request<PurchaseOption> request) {
                PurchaseOption purchase = request.getData();
                purchase.addToCart((Buyer) request.getUser());
            }

            @Override
            public void setNextHandler(RequestHandler<PurchaseOption> nextHandler) {

            }
        },
                        Set.of(Role.BUYER))
        ).handleRequest(new Request<>(purchaseOption, buyer));
    }

    public Order placeOrder(Buyer buyer, PaymentType paymentType) {
        final Order[] order = new Order[1];
        new AuthenticationMiddleware(new AuthorizationMiddleware(new RequestHandler() {
            @Override
            public void handleRequest(Request request) {
                order[0] = orderController.createOrder((Buyer) request.getUser(), paymentType);
            }

            @Override
            public void setNextHandler(RequestHandler nextHandler) {

            }
        }, Set.of(Role.BUYER))).handleRequest(new Request(null, buyer));
        return order[0];
    }

    public void payOrder(Buyer buyer, Order order) {
        new AuthenticationMiddleware<Order>(new AuthorizationMiddleware<>(new RequestHandler<Order>() {
            @Override
            public void handleRequest(Request<Order> request) {
                request.getData().pay();
            }

            @Override
            public void setNextHandler(RequestHandler<Order> nextHandler) {}

        }, Set.of(Role.BUYER))).handleRequest(new Request<>(order, buyer));
    }

    public List<SoldUnit> getSoldBooks(Seller seller) {
        final List<SoldUnit> soldUnits = new ArrayList<>();
        new AuthenticationMiddleware<>(new AuthenticationMiddleware<>(new RequestHandler() {
            @Override
            public void handleRequest(Request request) {
                soldUnits.addAll(seller.getSoldBooks());
            }

            @Override
            public void setNextHandler(RequestHandler nextHandler) {

            }
        })).handleRequest(new Request(null, seller));
        return soldUnits;
    }

    public List<OrderItem> getPurchasedBooks(Buyer buyer) {
        final List<OrderItem> purchasedBooks = new ArrayList<>();
        new AuthenticationMiddleware(new AuthorizationMiddleware(new RequestHandler() {
            @Override
            public void handleRequest(Request request) {
                purchasedBooks.addAll(buyer.getPurchasedBooks());
            }

            @Override
            public void setNextHandler(RequestHandler nextHandler) {

            }
        }, Set.of(Role.BUYER))).handleRequest(new Request(null, buyer));
        return purchasedBooks;
    }

    public String downloadFile(Buyer buyer, String fileId, String sellerId) {
        final String[] fileContent = {""};
        new AuthenticationMiddleware<String>(new AuthorizationMiddleware<>(new RequestHandler<String>() {
            @Override
            public void handleRequest(Request<String> request) {
               fileContent[0] = storageController.downloadFile(request.getUser(), request.getData());
            }

            @Override
            public void setNextHandler(RequestHandler<String> nextHandler) {

            }
        }, Set.of(Role.BUYER))).handleRequest(new Request<>(fileId + ":" +sellerId, buyer));
        return fileContent[0];
    }
}
