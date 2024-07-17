package fr.eni.encheres.dtos;

import jakarta.validation.constraints.Min;
import java.time.LocalDate;

public class BidDto {

    private LocalDate bidDate;
    @Min(value = 1, message = "You must enter an offer")
    private int offer;
    @Min(value = 1, message = "A bid must have a bidder")
    private int bidderId;
    @Min(value = 1, message = "A bid must have a product")
    private int productId;

    public LocalDate getBidDate() {
        return bidDate;
    }

    public void setBidDate(LocalDate bidDate) {
        this.bidDate = bidDate;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
