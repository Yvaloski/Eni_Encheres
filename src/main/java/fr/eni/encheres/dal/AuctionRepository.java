package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
