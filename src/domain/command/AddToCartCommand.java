package domain.command;

import entities.user.buyer.Buyer;

public interface AddToCartCommand {
    void addToCart(Buyer buyer);
}
