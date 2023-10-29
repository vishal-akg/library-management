package entities.order;

import entities.payment.Payment;
import entities.payment.PaymentFactory;
import entities.payment.PaymentType;
import entities.storage.controller.StorageController;
import entities.user.buyer.Buyer;

import java.util.stream.Collectors;

public class OrderController {
    private static OrderController instance;

    public static OrderController getInstance() {
        if (instance == null) {
            synchronized (OrderController.class) {
                if (instance == null) {
                    instance = new OrderController();
                }
            }
        }
        return instance;
    }

    public Order createOrder(Buyer buyer, PaymentType paymentType) {

        Payment payment = PaymentFactory.createPayment(paymentType, buyer.getCart().getTotalCost());
        Order order = Order.Builder.newBuilder()
                .storageController(StorageController.getInstance())
                .buyer(buyer)
                .payment(payment)
                .orderItems(
                        buyer.getCart().getCartItems()
                                .stream()
                                .map(item ->
                                        new OrderItem(
                                                item.getSeller(),
                                                item.getBook(),
                                                item.getPrice()
                                        )
                                ).collect(Collectors.toList())
                )
                .build();
        return order;
    }

    public void pay(Order order) {
        order.pay();
    }
}
