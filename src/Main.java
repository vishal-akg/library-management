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


        System.out.println();
        System.out.println("Search results =================");
        List<CatalogueItem> filteredBooks = controller.searchBooks(
                buyer,
                Map.of("title", new TitleSpec("distributed system"),
                        "author", new AuthorSpec("martin Kleppmann")
                )
        );
        System.out.println(filteredBooks);
        for (CatalogueItem item: filteredBooks) {
            System.out.println("Title: " + item.getTitle() + ", Publisher " + item.getPublisher());
        }
        System.out.println("Search results ==================");

        System.out.println();

        System.out.println("Placing order:======================");
        controller.addBookToCart(buyer, filteredBooks.get(0).getPurchaseOptions().get(0));
        Order order = controller.placeOrder(buyer, PaymentType.UPI);
        controller.payOrder(buyer, order);
        System.out.println("Placing order:======================");

        System.out.println();

        System.out.println("Sold books:======================");
        List<SoldUnit> soldUnits = controller.getSoldBooks(seller);
        for (SoldUnit unit: soldUnits) {
            System.out.println("Title: " + unit.getTitle() + ", Price: " + unit.getPrice() + ", Date: " + unit.getDate());

        }
        System.out.println("Sold books:======================");
        System.out.println();

        List<OrderItem> purchasedBooks = controller.getPurchasedBooks(buyer);
        System.out.println("Purchased books :=====================");
        for (OrderItem item: purchasedBooks) {
            System.out.println("Title:" + item.getBook().getTitle());
        }
        System.out.println("Purchased books :=====================");
        System.out.println();


        OrderItem orderItem = purchasedBooks.get(0);
        String content = controller.downloadFile(buyer, orderItem.getBook().getBookId(), orderItem.getSeller().getId());
        System.out.println("file content :=" + content);
    }
}