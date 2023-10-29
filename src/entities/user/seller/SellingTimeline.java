package entities.user.seller;

import valueobjects.BookId;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SellingTimeline {
    private List<SoldUnit> soldUnits;
    public SellingTimeline() {
        this.soldUnits = new LinkedList<>();
    }

    public void addBook(String title, Double price) {
        this.soldUnits.add(new SoldUnit(title, price));
    }

    public List<SoldUnit> getSoldUnits() {
        return soldUnits;
    }
}
