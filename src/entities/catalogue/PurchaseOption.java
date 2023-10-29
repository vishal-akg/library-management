package entities.catalogue;

import domain.command.AddToCartCommand;
import entities.user.buyer.Buyer;
import entities.user.seller.Seller;

public class PurchaseOption implements AddToCartCommand {
    private CatalogueItem book;
    private Seller seller;
    private Double unitPrice;

    private PurchaseOption(Builder builder) {
        book = builder.book;
        seller = builder.seller;
        unitPrice = builder.unitPrice;
    }

    @Override
    public void addToCart(Buyer buyer) {
        buyer.addToCart(book, seller, unitPrice);
    }

    public String getSellerId() {
        return seller.getId();
    }

    public Seller getSeller() { return seller; }

    public Double getUnitPrice() {
        return unitPrice;
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private CatalogueItem book;
        private Seller seller;
        private Double unitPrice;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder book(CatalogueItem val) {
            book = val;
            return this;
        }

        public Builder seller(Seller val) {
            seller = val;
            return this;
        }

        public Builder unitPrice(Double val) {
            unitPrice = val;
            return this;
        }

        public PurchaseOption build() {
            return new PurchaseOption(this);
        }
    }
}
