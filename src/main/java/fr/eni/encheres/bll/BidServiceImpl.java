package fr.eni.encheres.bll;


import fr.eni.encheres.bll.services.BidService;
import fr.eni.encheres.bo.Bid;

import fr.eni.encheres.dal.BidRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    BidRepository bidRepo;

    public BidServiceImpl(BidRepository bidRepo) {
        this.bidRepo = bidRepo;
    }

    @Override
    public void addBid(Bid bid) {

        //vérif que l'enchérisseur n'est ni le vendeur, ni l'enchérisseur gagnant actuel
        //le montant de l'offre doit être strictement supérieur à la dernière offre
        //le montant ne peut être supérieur au crédit de l'enchérisseur
        //débit du montant enchérit
        //recréditage du précédent montant à l'enchérisseur précédent

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
