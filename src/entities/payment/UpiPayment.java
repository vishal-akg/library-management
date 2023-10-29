package entities.payment;

public class UpiPayment extends Payment{
    public UpiPayment(Double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {

    }
}
