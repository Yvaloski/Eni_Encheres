package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySellerIdUser(long id);

    @Query(value = "SELECT DISTINCT p.id_product as id_prod, p.name_product, p.description_product, "
            + "p.auction_start, p.auction_end, p.start_price, p.final_price, p.url_img, "
            + "c.label as category, "
            + "u.username as seller, "
            + "(SELECT MAX(b.offer)"
                + "FROM Bid b "
                + "LEFT JOIN Product p ON b.product_id = p.id_product "
                + "WHERE p.id_product = id_prod"
                + ") as highest_bid, "
            + "(SELECT u.username "
                + "FROM Bid b "
                + "LEFT JOIN Product p ON b.product_id = p.id_product "
                + "LEFT JOIN User u ON u.id_user = b.bidder_id "
                + "WHERE p.id_product = id_prod "
                + "AND b.offer = highest_bid"
                + ") as highest_bidder"
            + " from Product p"
            + " left join Bid b ON b.product_id = p.id_product"
            + " left join Category c ON c.id_cat = p.category_id_cat"
            + " left join User u ON u.id_user = p.seller_id_user"
            + " WHERE b.bidder_id = ?1"
            , nativeQuery = true)
    List<Map<String, Product>> findByBidderId(long id);

    @Query(value = "SELECT p.id_product as id_prod, p.name_product, p.description_product, "
            + "p.auction_start, p.auction_end, p.start_price, p.final_price, p.url_img, "
            + "c.label as category, "
            + "u.username as seller, "
            + "(SELECT MAX(b.offer)"
            + "FROM Bid b "
            + "LEFT JOIN Product p ON b.product_id = p.id_product "
            + "WHERE p.id_product = id_prod"
            + ") as highest_bid, "
            + "(SELECT u.username "
            + "FROM Bid b "
            + "LEFT JOIN Product p ON b.product_id = p.id_product "
            + "LEFT JOIN User u ON u.id_user = b.bidder_id "
            + "WHERE p.id_product = id_prod "
            + "AND b.offer = highest_bid"
            + ") as highest_bidder"
            + " from Product p"
            + " left join Category c ON c.id_cat = p.category_id_cat"
            + " left join User u ON u.id_user = p.seller_id_user"
            + " WHERE p.id_product = ?1"
            , nativeQuery = true)
    Map<String, Product> findProductById(Long id);

    List<Product> findProductsByCategory(Category category);
}
