package entities.user.factory;

import entities.user.Role;
import entities.user.User;
import entities.user.admin.Admin;
import entities.user.buyer.Buyer;
import entities.user.seller.Seller;

public class UserFactory {

    public static User createUser(Role role, String username, String password) {
        return switch(role) {
            case ADMIN -> new Admin(username, password);
            case SELLER -> new Seller(username, password);
            case BUYER -> new Buyer(username, password);
        };
    }
}
