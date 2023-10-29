package entities.payment;

public class PaymentFactory {
    public static Payment createPayment(PaymentType paymentType, Double amount) {
        return switch (paymentType) {
            case UPI -> new UpiPayment(amount);
            case NETBANKING -> null;
            case CARD -> null;
            case PAYPAL -> null;
        };
    }
}
