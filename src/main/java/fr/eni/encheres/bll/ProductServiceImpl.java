package fr.eni.encheres.bll;

import fr.eni.encheres.bll.services.ProductService;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.Category;
import fr.eni.encheres.bo.Product;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.BidRepository;
import fr.eni.encheres.dal.CategoryRepository;
import fr.eni.encheres.dal.ProductRepository;
import fr.eni.encheres.dal.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    CategoryRepository categoryRepository;
    ProductRepository productRepo;
    BidRepository bidRepo;
    UserRepository userRepo;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepo, BidRepository bidRepo, UserRepository userRepo) {
        this.categoryRepository = categoryRepository;
        this.productRepo = productRepo;
        this.bidRepo = bidRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addProduct(Product product) {
        this.validateProduct(product);
        if (product.getAuctionStart().equals(LocalDate.now())) {
            product.setSaleState("open");
        } else {
            product.setSaleState("upcoming");
        }
        productRepo.save(product);
    }

    private void validateProduct(Product product) {
        if (!product.getSeller().isActive()) {
            throw new RuntimeException("The user is not active, they cannot sell items");
        }
        if (product.getAuctionStart().isBefore(LocalDate.now())) {
            throw new RuntimeException("The auction must start today or later");
        }
        if(!product.getAuctionEnd().isAfter(product.getAuctionStart())) {
            throw new RuntimeException("The auction end Date must be after start Date");
        }
        if(product.getAuctionStart().plusMonths(1).isBefore(product.getAuctionEnd())) {
            throw new RuntimeException("The auction cannot last for more than one month");
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        System.out.println(products);
        return products;
    }

    @Override
    public Product getProductById(long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        if (!product.getAuctionStart().isAfter(LocalDate.now())) {
            throw new RuntimeException("The auction started, you cannot modify it anymore");
        }
        if(product.getAuctionEnd().isBefore(LocalDate.now())) {
            throw new RuntimeException("The auction ended, you cannot modify it anymore");
        }
        this.validateProduct(product);
        productRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getSalesByUserId(long id) {
        return productRepo.findBySellerIdUser(id);
    }

    @Override
    public List<Map<String, Product>> getOffersByUserId(long id) {
        return productRepo.findByBidderId(id);
    }

    @Override
    public Map<String, Product> getByIdProduct(long id) {
        return productRepo.findProductById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String label) {
        Category category = categoryRepository.findCategoryByLabel(label).orElse(null);
        if (category == null) {
            return null;
        }
        return productRepo.findProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepo.findProductsByNameProductContaining(name);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateSaleStateDaily () {
        List<Product> lstStartingAuctions = productRepo.findByAuctionStart(LocalDate.now());
        List<Product> lstClosingAuctions = productRepo.findByAuctionEnd(LocalDate.now());

        for (Product product : lstStartingAuctions) {
            product.setSaleState("open");
            productRepo.save(product);
        }
        for (Product product : lstClosingAuctions) {
            product.setSaleState("closed");
            Bid highestBid = this.getHighestBid(product.getIdProduct());
            if (highestBid != null) {
                User seller = product.getSeller();
                seller.setCredit(seller.getCredit() + highestBid.getOffer());
                userRepo.save(seller);
                //ajout noptifications pour venduer et acheteur à implémenter
                product.setFinalPrice(highestBid.getOffer());
            }
            productRepo.save(product);
        }
    }

    private Bid getHighestBid(long  idProduct) {
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
