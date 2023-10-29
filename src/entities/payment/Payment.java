package entities.payment;

import entities.payment.observer.PaymentObservable;
import entities.payment.observer.PaymentObserver;
import entities.payment.state.PaymentCreatedState;
import entities.payment.state.PaymentPendingState;
import entities.payment.state.PaymentState;
import entities.payment.state.PaymentSuccessfulState;
import valueobjects.TransactionId;

import java.util.ArrayList;
import java.util.List;

public abstract class Payment implements PaymentObservable {
    protected TransactionId transactionId;
    protected Double amount;
    private PaymentState paymentState;
    private List<PaymentObserver> paymentObservers;

    public Payment(Double amount) {
        this.amount = amount;
        this.paymentState = new PaymentCreatedState();
        this.paymentObservers = new ArrayList<>();
    }

    @Override
    public void addObserver(PaymentObserver observer) {
        paymentObservers.add(observer);
    }

    @Override
    public void removeObserver(PaymentObserver observer) {
        paymentObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PaymentObserver observer: paymentObservers) {
            observer.update(this);
        }
    }

    public void setPaymentState(PaymentState state) {
        this.paymentState = state;
        notifyObservers();
    }
    public abstract void processPayment();

    public void pay() {
        paymentState.pay(this);
    }
    public boolean isPaid() {
        return paymentState instanceof PaymentSuccessfulState;
    }
}
