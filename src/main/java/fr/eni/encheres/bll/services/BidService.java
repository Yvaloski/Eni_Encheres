package fr.eni.encheres.bll.services;


import fr.eni.encheres.bo.Bid;

import java.util.List;

public interface BidService {
    void addBid(Bid bid);

    Bid getBidById(long id);

    List<Bid> getBids();

    void deleteBid(long id);

    void updateBid(Bid bid);

}
