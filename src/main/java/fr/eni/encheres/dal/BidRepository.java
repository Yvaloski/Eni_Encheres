package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByProductIdProduct(long idProduct);

    List<Bid> findByBidder(User user);
}
