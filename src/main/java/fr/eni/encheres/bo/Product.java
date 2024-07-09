package fr.eni.encheres.bo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private LocalDate auctionStart;
    private LocalDate auctionEnd;
    private int startPrice;
    private int finalPrice;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, targetEntity = User.class)
    User seller;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, targetEntity = Categorie.class)
    Categorie category;
    private String saleState;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, String descriptionProduct, LocalDate auctionStart, LocalDate auctionEnd, int startPrice, int finalPrice, User seller, Categorie category, String saleState) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.auctionStart = auctionStart;
        this.auctionEnd = auctionEnd;
        this.startPrice = startPrice;
        this.finalPrice = finalPrice;
        this.seller = seller;
        this.category = category;
        this.saleState = saleState;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public LocalDate getAuctionStart() {
        return auctionStart;
    }

    public void setAuctionStart(LocalDate auctionStart) {
        this.auctionStart = auctionStart;
    }

    public LocalDate getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(LocalDate auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Categorie getCategory() {
        return category;
    }

    public void setCategory(Categorie category) {
        this.category = category;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
    }
}