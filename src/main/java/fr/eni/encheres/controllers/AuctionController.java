package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.AuctionService;
import fr.eni.encheres.bo.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/all")
    public List<Auction> getAuction() {
        return auctionService.getAuctions();
    }

    @GetMapping("/{id}")
    public Auction getAuctionById(@PathVariable long id) {
        return auctionService.getAuctionById(id);
    }

    @PostMapping("/new")
    public Auction addAuction(@RequestBody Auction auction) {
        auctionService.addAuction(auction);
        return auction;
    }

    @PostMapping("/{id}/edit")
    public String editAuction(@PathVariable long id, @RequestBody Auction auction) {
        auctionService.updateAuction(auction);
        return "Auction edited";
    }

    @GetMapping("/{id}/cancel")
    public String cancelAuction(@PathVariable long id) {
        auctionService.deleteAuction(id);
        return "Auction cancelled";
    }
}
