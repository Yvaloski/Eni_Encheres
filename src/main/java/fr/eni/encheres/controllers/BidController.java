package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.BidService;
import fr.eni.encheres.bll.services.ProductService;
import fr.eni.encheres.bll.services.UserService;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.Product;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dtos.BidDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/bids")
public class BidController {

    UserService userService;
    ProductService productService;
    BidService bidService;

    public BidController(BidService bidService, UserService userService, ProductService productService
    ) {
        this.productService = productService;
        this.bidService = bidService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Bid> getBids() {
        return bidService.getBids();
    }

    @GetMapping("/{id}")
    public Bid getBidById(@PathVariable long id) {
        return bidService.getBidById(id);
    }

    @PostMapping("/new")
    public Bid addBid(@RequestBody BidDto bidDTO) {
        Bid bid = new Bid();
        bid.setBidDate(bidDTO.getBidDate());
        bid.setOffer(bidDTO.getOffer());

        User bidder = userService.getUserById(bidDTO.getBidderId());
        bid.setBidder(bidder);

        Product product = productService.getProductById(bidDTO.getProductId());
        bid.setProduct(product);

        bidService.addBid(bid);
        return bid;
    }

    @PostMapping("/{id}/edit")
    public String editBid(@PathVariable long id, @RequestBody Bid bid) {
        bidService.updateBid(bid);
        return "Bid edited";
    }

    @GetMapping("/{id}/cancel")
    public String cancelAuction(@PathVariable long id) {
        bidService.deleteBid(id);
        return "Bid cancelled";
    }
}
