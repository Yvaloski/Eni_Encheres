package fr.eni.encheres.bo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuction;

    @Column
    private LocalDate auctionDate;

    @Column
    private int auctionPrice;

    public auction(int idAuction, LocalDate auctionDate, int auctionPrice) {
        this.idAuction = idAuction;
        this.auctionDate = auctionDate;
        this.auctionPrice = auctionPrice;
    }

    public auction() {

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

    @Override
    public String toString() {
        return "auction{" +
                "idAuction=" + idAuction +
                ", auctionDate=" + auctionDate +
                ", auctionPrice=" + auctionPrice +
                '}';
    }
}
