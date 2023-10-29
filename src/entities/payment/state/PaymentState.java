package entities.payment.state;

import entities.payment.Payment;

public interface PaymentState {
    void pay(Payment payment);
}
