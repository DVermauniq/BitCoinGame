package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.dtos.AuctionCardDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.service.AuctionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuctionCardController {

    @Autowired
    private AuctionCardService auctionCardService;

    @PostMapping("/create-auction-card")
    public ResponseEntity<?> createAuctionCard(@RequestBody AuctionCardDto auctionCardDTO) {
        // Assuming you have authentication and the username is available in the security context
        String firstName = "logged-in-username"; // Get username from security context

        Game game = auctionCardService.createAuctionCard(auctionCardDTO, firstName);
        if (game != null) {
            return new ResponseEntity<>(game, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create auction card", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{gameId}/participants/{userId}")
    public ResponseEntity<String> addParticipantToAuction(@PathVariable Long gameId, @PathVariable Long clubId, @RequestBody UserDto userDto) {
        try {
            auctionCardService.addUserToAuctionFromClub(clubId, gameId, userDto);
            return ResponseEntity.ok("User added to the auction successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user to the auction");
        }
    }
}
