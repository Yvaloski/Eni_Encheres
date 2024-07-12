package fr.eni.encheres.bo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBid;

    @Column
    private LocalDate bidStartDate;
    @Column
    private LocalDate bidEndDate;

    @Column
    private int offer;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    User bidder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    public Bid() {

    }

    public LocalDate getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(LocalDate bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public LocalDate getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(LocalDate bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public int getIdBid() {
        return idBid;
    }

    public void setIdBid(int idBid) {
        this.idBid = idBid;
    }


    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "idBid=" + idBid +
                ", bidDate=" + bidStartDate +
                ", offer=" + offer +
                ", bidder=" + bidder +
                ", product=" + product +
                '}';
    }
}
