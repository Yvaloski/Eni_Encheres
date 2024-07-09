package fr.eni.encheres.bo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuction;

    @Column
    private LocalDate auctionDate;

    @Column
    private int auctionPrice;

    public Auction(int idAuction, int auctionPrice, LocalDate auctionDate, User auction) {
        this.idAuction = idAuction;
        this.auctionPrice = auctionPrice;
        this.auctionDate = auctionDate;
        this.auction = auction;
    }

    public Auction() {

    }

    public int getIdAuction() {
        return idAuction;
    }

    public void setIdAuction(int idAuction) {
        this.idAuction = idAuction;
    }

    public LocalDate getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(LocalDate auctionDate) {
        this.auctionDate = auctionDate;
    }

    public int getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(int auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false,targetEntity = User.class)
    User auction;

    @Override
    public String toString() {
        return "auction{" +
                "idAuction=" + idAuction +
                ", auctionDate=" + auctionDate +
                ", auctionPrice=" + auctionPrice +
                '}';
    }
}
