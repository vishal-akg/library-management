package entities.payment.state;

import entities.order.state.OrderPaidState;
import entities.payment.Payment;

public class PaymentCreatedState implements PaymentState{
    @Override
    public void pay(Payment payment) {
        payment.processPayment();
        payment.setPaymentState(new PaymentSuccessfulState());
    }
}
