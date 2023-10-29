package entities.payment.observer;

import entities.payment.Payment;

public interface PaymentObserver {
    void update(Payment payment);
}
