import entities.catalogue.CatalogueItem;
import entities.catalogue.search.spec.AuthorSpec;
import entities.catalogue.search.spec.TitleSpec;
import entities.order.Order;
import entities.order.OrderItem;
import entities.payment.PaymentType;
import entities.user.Role;
import entities.user.buyer.Buyer;
import entities.user.seller.InventoryItem;
import entities.user.seller.Seller;
import entities.user.seller.SoldUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws Exception {
        LibraryController controller = new LibraryController();
        controller.signup("seller1", "123", Role.SELLER);
        Seller seller = (Seller) controller.login("seller1", "123");

        controller.addBook(
                InventoryItem.Builder.newBuilder()
                        .author("Martin Kleppmann")
                        .edition("first")
                        .title("Distributed system")
                        .publisher("O'Reilly")
                        .price(2000.0)
                        .content("Design data intensive application")
                .build(), seller
        );

        controller.signup("vishal_cms", "123", Role.BUYER);
        Buyer buyer = (Buyer) controller.login("vishal_cms", "123");
        List<CatalogueItem> books = controller.getBookCatalogue(buyer);

        System.out.println(books);
        List<CatalogueItem> filteredBooks = controller.searchBooks(
                buyer,
                Map.of("title", new TitleSpec("distributed system"),
                        "author", new AuthorSpec("martin Kleppmann")
                )
        );
        System.out.println("Search results =================");
        System.out.println(filteredBooks);
        System.out.println("================================");

        controller.addBookToCart(buyer, filteredBooks.get(0).getPurchaseOptions().get(0));
        Order order = controller.placeOrder(buyer, PaymentType.UPI);
        controller.payOrder(buyer, order);

        List<SoldUnit> soldUnits = controller.getSoldBooks(seller);
        System.out.println("sold units :=" + soldUnits);

        List<OrderItem> purchasedBooks = controller.getPurchasedBooks(buyer);
        System.out.println("purchased books :=" + purchasedBooks);
        OrderItem orderItem = purchasedBooks.get(0);
        String content = controller.downloadFile(buyer, orderItem.getBook().getBookId(), orderItem.getSeller().getId());
        System.out.println("file content :=" + content);
    }
}