package entities.order.state;

import entities.order.Order;

public interface OrderState {
    void pay(Order order);
}
