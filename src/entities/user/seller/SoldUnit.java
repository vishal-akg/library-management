package entities.user.seller;

import valueobjects.BookId;

import java.util.Date;

public class SoldUnit {
    private String title;
    private Date unit;
    private Double price;

    public SoldUnit(String title, Double price) {
        this.title = title;
        this.price = price;
    }
}
