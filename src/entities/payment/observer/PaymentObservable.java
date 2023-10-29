package entities.payment.observer;

public interface PaymentObservable {
    void addObserver(PaymentObserver observer);
    void removeObserver(PaymentObserver observer);
    void notifyObservers();
}
