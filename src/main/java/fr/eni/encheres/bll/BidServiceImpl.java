package fr.eni.encheres.bll;


import fr.eni.encheres.bll.services.BidService;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.BidRepository;
import fr.eni.encheres.dal.UserRepository;
import fr.eni.encheres.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    BidRepository bidRepo;
    UserRepository userRepo;

    public BidServiceImpl(BidRepository bidRepo, UserRepository userRepo) {
        this.bidRepo = bidRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addBid(Bid bid) {

        User precedentBidder = new User();

        if (!bid.getBidder().isActive()) {
            throw new RuntimeException("The user isn't active, they cannot bid");
        }
        if (bid.getProduct() == null) {
            throw new ProductNotFoundException("Product Not Found");
        }
        if (bid.getBidDate().isBefore(bid.getProduct().getAuctionStart()) ) {
            throw new RuntimeException("The auction hasn't started yet.");
        }
        if (bid.getBidDate().isAfter(bid.getProduct().getAuctionEnd()) ) {
            throw new RuntimeException("The auction has ended.");
        }
        long idProduct = bid.getProduct().getIdProduct();

        User newBidder = bid.getBidder();

        //recherche de l'enchère précédente sur le produit
        Bid precedentBid = this.getHighestBid(idProduct);

        if (precedentBid == null) {
            //Aucun enchérisseur précédent => 1ère enchèere
            if (bid.getOffer() < bid.getProduct().getStartPrice()) {
                throw new RuntimeException("The offer must be greater than the starting price");
            }
        }
        else  {
            //surenchère
            precedentBidder = precedentBid.getBidder();
            //vérif que les deux bidders sont diifférents
            if (newBidder.getIdUser() == precedentBidder.getIdUser()) {
                throw new RuntimeException("The user is already the highest bidder");
            }
            //le montant de l'offre doit être strictement supérieur à la dernière offre
            if (bid.getOffer() <= precedentBid.getOffer()) {
                throw new RuntimeException("The new offer must be higher than the current offer");
            }
        }

        //vérifs communes aux deux cas
        //vérif que l'enchérisseur n'est pas le vendeur
        if (newBidder.getIdUser() == bid.getProduct().getSeller().getIdUser()) {
            throw new RuntimeException("The user can't bid on his own sale");
        }

        //le montant ne peut être supérieur au crédit de l'enchérisseur
        if (bid.getOffer() > newBidder.getCredit()) {
            throw new RuntimeException("The user doesn't have enough credit");
        }

        //sauvegarde de la nouvelle offre
        bidRepo.save(bid);

        //débit du montant enchéri
        newBidder.setCredit(newBidder.getCredit() - bid.getOffer());
        userRepo.save(newBidder);

        //recréditage du précédent montant à l'enchérisseur précédent
        if (precedentBid != null) {
            precedentBidder.setCredit(precedentBidder.getCredit() + bid.getOffer());
            userRepo.save(precedentBidder);
        }

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

    @Override
    public Bid getHighestBid(long idProduct) {
        List<Bid> lstBids =  bidRepo.findByProductIdProduct(idProduct);
        if (lstBids.isEmpty()) {
            return null;
        }
        else {
            int bestOffer = 0;
            Bid bestBid = null;

            for (Bid Bid : lstBids) {
                if (Bid.getOffer() > bestOffer) {
                    bestOffer = Bid.getOffer();
                    bestBid = Bid;
                }
            }
            return bestBid;
        }
    }
}
