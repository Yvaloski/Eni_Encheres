package fr.eni.encheres.bo;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBid;

    @Column
    private LocalDate bidDate;

    @Column
    private int offer;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    User bidder;

    @ManyToOne( targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;

    public Bid() {

    }

    public int getIdBid() {
        return idBid;
    }

    public void setIdBid(int idBid) {
        this.idBid = idBid;
    }

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
                ", bidDate=" + bidDate +
                ", offer=" + offer +
                ", bidder=" + bidder +
                ", product=" + product +
                '}';
    }
}
