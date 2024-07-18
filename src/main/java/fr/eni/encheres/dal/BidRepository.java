package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByProductIdProduct(long idProduct);

    List<Bid> findByBidder(User user);
}
