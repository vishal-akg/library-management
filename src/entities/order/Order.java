package entities.order;

import entities.order.state.OrderCreatedState;
import entities.order.state.OrderState;
import entities.payment.Payment;
import entities.payment.observer.PaymentObserver;
import entities.storage.controller.StorageController;
import entities.user.buyer.Buyer;

import java.util.List;

public class Order implements PaymentObserver {
    private Buyer buyer;
    private List<OrderItem> orderItems;
    private OrderState orderState;
    private StorageController storageController;
    private Payment payment;

    private Order(Builder builder) {
        buyer = builder.buyer;
        orderItems = builder.orderItems;
        storageController = builder.storageController;
        payment = builder.payment;
        orderState = new OrderCreatedState();
        payment.addObserver(this);
    }

    @Override
    public void update(Payment payment) {
        if (payment.isPaid()) {
            for (OrderItem orderItem: orderItems) {
                String fileId = storageController.grantUserReadAccess(
                        orderItem.getBook().getBookId(),
                        orderItem.getSeller().getId(),
                        buyer.getId()
                );
                buyer.addPurchasedBooks(orderItem);
                orderItem.getSeller().addSoldBook(orderItem.getBook().getTitle(), orderItem.getPrice());
            }
        }
    }

    public void pay() {
        payment.pay();
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private Buyer buyer;
        private List<OrderItem> orderItems;
        private StorageController storageController;
        private Payment payment;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder buyer(Buyer val) {
            buyer = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            orderItems = val;
            return this;
        }

        public Builder storageController(StorageController val) {
            storageController = val;
            return this;
        }

        public Builder payment(Payment val) {
            payment = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
