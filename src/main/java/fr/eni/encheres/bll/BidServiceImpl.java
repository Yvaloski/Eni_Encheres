package fr.eni.encheres.bll;


import fr.eni.encheres.bo.Bid;

import fr.eni.encheres.dal.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService{

    BidRepository bidRepo;

    public BidServiceImpl(BidRepository bidRepo) {
        this.bidRepo = bidRepo;
    }

    @Override
    public void addBid(Bid bid) {
        bidRepo.save(bid);
    }

    @Override
    public Bid getBidById(long id) {
        return bidRepo.findById(id).orElse(null);
    }

    @Override
    public List<Bid> getBids() {return bidRepo.findAll();
    }

    @Override
    public void deleteBid(long id) {bidRepo.deleteById(id);
    }

    @Override
    public void updateBid(Bid bid) {
        bidRepo.save(bid);
    }
}
