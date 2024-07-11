package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Auction;
import fr.eni.encheres.dal.AuctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService{

    AuctionRepository auctionRepo;

    public AuctionServiceImpl(AuctionRepository auctionRepo) {
        this.auctionRepo = auctionRepo;
    }

    @Override
    public void addAuction(Auction auction) {
        auctionRepo.save(auction);
    }

    @Override
    public Auction getAuctionById(long id) {
        return auctionRepo.findById(id).orElse(null);
    }

    @Override
    public List<Auction> getAuctions() {
        return auctionRepo.findAll();
    }

    @Override
    public void deleteAuction(long id) {
        auctionRepo.deleteById(id);
    }

    @Override
    public void updateAuction(Auction auction) {
        auctionRepo.save(auction);
    }
}
