package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySellerIdUser(long id);

    @Query(value = "SELECT DISTINCT p.id_product as idProduct, "
            + "p.name_product as nameProduct, "
            + "p.description_product as descriptionProduct, "
            + "p.auction_start as auctionStart, "
            + "p.auction_end as auctionEnd, "
            + "p.start_price as startPrice, "
            + "p.final_price as finalPrice, "
            + "p.url_img as urlImg, "
            + "c.label as categoryLabel, "
            + "u.pseudo as sellerUsername, "
            + "(SELECT MAX(b.offer)"
            + "FROM Bid b "
            + "LEFT JOIN Product p ON b.product_id = p.id_product "
            + "WHERE p.id_product = idProduct"
            + ") as highestBid, "
            + "(SELECT u.pseudo "
            + "FROM Bid b "
            + "LEFT JOIN Product p ON b.product_id = p.id_product "
            + "LEFT JOIN User u ON u.id_user = b.bidder_id "
            + "WHERE p.id_product = idProduct "
            + "AND b.offer = highestBid "
            + "LIMIT 1"
            + ") as highestBidder"
            + " from Product p"
            + " left join Bid b ON b.product_id = p.id_product"
            + " left join Category c ON c.id_cat = p.category_id_cat"
            + " left join User u ON u.id_user = p.seller_id_user"
            + " WHERE b.bidder_id = ?1"
            , nativeQuery = true)
    List<Map<String, Product>> findByBidderId(long id);

    @Query(value = "SELECT p.id_product as idProduct, "
            + "p.name_product as nameProduct, "
            + "p.description_product as descriptionProduct, "
            + "p.auction_start as auctionStart, "
            + "p.auction_end as auctionEnd, "
            + "p.start_price as startPrice, "
            + "p.final_price as finalPrice, "
            + "p.url_img as urlImg, "
            + "c.label as categoryLabel, "
            + "u.pseudo as sellerUsername, "
            + "u.address as address, "
            + "u.postal_code as postalCode, "
            + "u.city as city, "
            + "(SELECT MAX(b.offer)"
                + "FROM Bid b "
                + "LEFT JOIN Product p ON b.product_id = p.id_product "
                + "WHERE p.id_product = idProduct"
                + ") as highestBid, "
            + "(SELECT u.pseudo "
                + "FROM Bid b "
                + "LEFT JOIN Product p ON b.product_id = p.id_product "
                + "LEFT JOIN User u ON u.id_user = b.bidder_id "
                + "WHERE p.id_product = idProduct "
                + "AND b.offer = highestBid "
                + "LIMIT 1"
                + ") as highestBidder"
            + " from Product p"
            + " left join Category c ON c.id_cat = p.category_id_cat"
            + " left join User u ON u.id_user = p.seller_id_user"
            + " WHERE p.id_product = ?1"
            , nativeQuery = true)
    Map<String, Product> findProductById(Long id);

    List<Product> findProductsByCategory(Category category);

    List<Product> findProductsByNameProductContaining(String name);

    List<Product> findByAuctionStart(LocalDate date);

    List<Product> findByAuctionEnd(LocalDate date);
}
