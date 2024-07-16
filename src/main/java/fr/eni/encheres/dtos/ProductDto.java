package fr.eni.encheres.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class ProductDto {
    private int idProduct;
    @NotBlank(message = "Name of the product cannot be blank")
    @Length(min = 3, max = 40, message = "Name of the product must be between 3-40 characters")
    private String nameProduct;
    @NotBlank(message = "Description of the product cannot be blank")
    @Length(min = 3, max = 512, message = "Description of the product must be between 3-512 characters")
    private String descriptionProduct;
    @NotBlank(message = "The date of the opening of the auction cannot be blank")
    private LocalDate auctionStart;
    private LocalDate auctionEnd;
    @NotBlank(message = "You must enter a starting price")
    private int startPrice;
    private int finalPrice;
    @NotBlank(message = "The auction must have a seller")
    private int sellerId;
    @NotBlank(message = "The auction must have a category")
    private int categoryId;
    private String saleState;
    private String urlImg;

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
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

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
    }
}
