package fr.eni.encheres.controllers;

import fr.eni.encheres.bll.BidService;
import fr.eni.encheres.bo.Bid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/all")
    public List<Bid> getBids() {
        return bidService.getBids();
    }

    @GetMapping("/{id}")
    public Bid getBidById(@PathVariable long id) {
        return bidService.getBidById(id);
    }

    @PostMapping("/new")
    public Bid addBid(@RequestBody Bid bid) {
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
