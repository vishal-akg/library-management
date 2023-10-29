package entities.cart.visitor;

import entities.cart.CartItem;

public class CartTotalCost implements CartCost{
    private Double totalCost;

    public CartTotalCost() {
        totalCost = 0.0;
    }

    @Override
    public void accept(CartItem cartItem) {
        totalCost += cartItem.getPrice();
    }

    public Double getTotalCost() {
        return totalCost;
    }
}
