package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Auction;

import java.util.List;

public interface AuctionService {
    void addAuction(Auction auction);

    Auction getAuctionById(long id);

    List<Auction> getAuctions();

    void deleteAuction(long id);

    void updateAuction(Auction auction);
}
