package com.rwl.Bit_coin.service;


import com.rwl.Bit_coin.dtos.AuctionCardDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Game;

public interface AuctionCardService {
    Game createAuctionCard(AuctionCardDto auctionCardDto, String firstName);

    Game addUserToAuctionFromClub(Long clubId, Long gameId, UserDto userDTO);
}