package entities.cart.visitor;

import entities.cart.CartItem;

public interface CartCost {
    void accept(CartItem cartItem);
}
