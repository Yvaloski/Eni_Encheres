package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
