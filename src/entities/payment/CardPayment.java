package entities.payment;

public class CardPayment extends Payment{
    public CardPayment(Double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {

    }
}
