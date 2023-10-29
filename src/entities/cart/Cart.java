package entities.cart;

import entities.cart.visitor.CartTotalCost;
import entities.user.buyer.Buyer;

import java.util.ArrayList;
import java.util.List;

public class Cart{
    private List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public Double getTotalCost() {
        CartTotalCost costCalculator = new CartTotalCost();
        for (CartItem cartItem: cartItems) {
            costCalculator.accept(cartItem);
        }
        System.out.println("total cost" + costCalculator.getTotalCost());
        return costCalculator.getTotalCost();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
