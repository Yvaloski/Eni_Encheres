package fr.eni.encheres.bll;

import fr.eni.encheres.bll.services.BidService;
import fr.eni.encheres.bll.services.UserService;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.Product;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.BidRepository;
import fr.eni.encheres.dal.ProductRepository;
import fr.eni.encheres.dal.UserRepository;
import fr.eni.encheres.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    ProductRepository productRepo;
    UserRepository userRepo;
    BidRepository bidRepo;
    BidService bidService;

    public UserServiceImpl(ProductRepository productRepo, UserRepository userRepo, BidRepository bidRepo, BidService bidService) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.bidRepo = bidRepo;
        this.bidService = bidService;
    }

    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(long idUserToDelete) {
        User userToDelete = this.getUserById(idUserToDelete);
        //vérification de l'existence du compte dédié aux user deleted
        //et création le cas échéant
        User deletedAccount = userRepo.findByPseudo("deleted");
        if(deletedAccount == null) {
            deletedAccount = this.createDeletedAccount();
        }

        this.cancelOngoingAndFutureUserSales(userToDelete);
        this.cancelOngoingUserBids(userToDelete);

        //récupération des ventes passées et des enchères passées
        //après suppression des ventes en cours et des enchères sur les ventes en cours de l'user à supprimer
        List<Product> lstProducts = productRepo.findBySellerIdUser(idUserToDelete);
        List<Bid> lstBids = bidRepo.findByBidder(userToDelete);
        for (Product product : lstProducts) {
            product.setSeller(deletedAccount);
            productRepo.save(product);
        }
        for (Bid bid : lstBids) {
            bid.setBidder(deletedAccount);
            bidRepo.save(bid);
        }

        userRepo.deleteById(idUserToDelete);
    }

    private User createDeletedAccount() {
        User deletedAccount = new User();
        deletedAccount.setPseudo("deleted");
        deletedAccount.setEmail("deleted");
        return userRepo.save(deletedAccount);
    }

    @Override
    public User updateUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public User getUserByPseudoOrEmail(String pseudo, String email) {
        return userRepo.findByPseudoOrEmail(pseudo, email).orElse(null);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Boolean existsByPseudo(String pseudo) {
        return userRepo.existsByPseudo(pseudo);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User findByPseudo(String pseudo) {
        return userRepo.findByPseudo(pseudo);
    }

    @Override
    public void deactivateUser(long  id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        this.cancelOngoingAndFutureUserSales(user);
        this.cancelOngoingUserBids(user);
        user.setActive(false);
        userRepo.save(user);
    }

    @Override
    public void activateUser(long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setActive(true);
        userRepo.save(user);
    }

    private void cancelOngoingAndFutureUserSales(User user) {
        List<Product> lstSales = productRepo.findBySellerIdUser(user.getIdUser());
        for (Product product : lstSales) {
            //vente pas encore commencée : delete simple
            if (product.getAuctionStart().isAfter(LocalDate.now())) {
                productRepo.delete(product);
            }
            //vente en cours : recréditage du meilleur enchérisseur s'il y en a un + suppression de la vente
            else if (product.getAuctionEnd().isAfter(LocalDate.now())) {
                Bid bid = bidService.getHighestBid(product.getIdProduct());
                if (bid != null) {
                    User bidder = bid.getBidder();
                    bidder.setCredit(bidder.getCredit() + bid.getOffer());
                    userRepo.save(bidder);
                }
                productRepo.delete(product);
            }
            //les ventes passées sont intouchées
        }
    }

    private void cancelOngoingUserBids(User user) {
        boolean OK = false;
        List<Bid> lstBids = bidRepo.findByBidder(user);
        for (Bid bid : lstBids) {
            if (bid.getProduct().getAuctionEnd().isAfter(LocalDate.now())) {
                //l'utilisateur a enchéri sur une vente en cours
                Bid highestBid = bidService.getHighestBid(bid.getProduct().getIdProduct());
                if (highestBid.getIdBid() == bid.getIdBid()) {
                    //l'utilsiateur est actuellement le meilleur enchérisseur sur une vente en cours=>
                    //recréditage de l'utilisateur*
                    //suppression du bid
                    user.setCredit(user.getCredit() + bid.getOffer());
                    userRepo.save(user);
                    bidRepo.delete(bid);
                    //boucle :
                    //récupération du nouveau highestBid pour la vente en question
                    //vérification que l'utilsiateur lié a suffisament de crédit
                    //si oui, débitage et fin
                    //si non, suppression du bid et nouveau tour de boucle
                    while (!OK) {
                        highestBid = bidService.getHighestBid(bid.getProduct().getIdProduct());
                        if (highestBid == null) {
                            OK = true;
                            continue;
                        }
                        if (highestBid.getOffer() <= highestBid.getBidder().getCredit()) {
                            highestBid.getBidder().setCredit(highestBid.getBidder().getCredit() - highestBid.getOffer());
                            userRepo.save(highestBid.getBidder());
                            OK = true;
                        } else {
                            bidRepo.delete(highestBid);
                        }
                    }

                } else {
                    bidRepo.delete(bid);
                }
            }
            //les offres faite ssur des ventes passées sont intouchées
        }
    }

}
