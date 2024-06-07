package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.dtos.AuctionCardDto;
import com.rwl.Bit_coin.entity.Game;

public interface AuctionCardService {
   Game createAuctionCard(AuctionCardDto auctionCardDto,String firstName);
   void addUserToAuction(Long gameId, Long userId);
}