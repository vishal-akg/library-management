package entities.user.seller;

import valueobjects.BookId;

import java.util.Date;

public class SoldUnit {
    private String title;
    private Date date;
    private Double price;

    public SoldUnit(String title, Double price) {
        this.title = title;
        this.price = price;
        this.date = new Date();
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }
}
