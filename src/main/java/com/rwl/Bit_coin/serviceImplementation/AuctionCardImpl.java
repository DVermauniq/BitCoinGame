package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.AuctionCardDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Club;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.enumm.GameType;
import com.rwl.Bit_coin.repo.ClubRepository;
import com.rwl.Bit_coin.repo.GameRepo;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.AuctionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuctionCardImpl implements AuctionCardService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepo gameRepository;

    @Override
    public Game createAuctionCard(AuctionCardDto auctionCardDto, Long userId,String password,Long gameId) {
        User user = userRepository.findById(userId).orElseThrow();
        Game game=gameRepository.findById(gameId).orElseThrow();
        if ( !user.getPassword().equals(auctionCardDto.getPassword())) {
            return null;
        }

//        Game game = new Game();
        game.setGameType(GameType.AUCTION); // Assuming Auction is a game type
        game.setNumberOfPlayers(auctionCardDto.getNoOfPeople().longValue());
        game.setAmountPerPerson(auctionCardDto.getAmountPerPerson().doubleValue());
        //    game.setDate(auctionCardDTO.getDate());
        // Set other properties from DTO
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        game.setUser(users);
        //  user.setGames(users);
        return gameRepository.save(game);
    }

    @Override
    public void addUserToAuctionFromClub(Long clubId, Long gameId,Long userId, UserDto userDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new RuntimeException("Club not found"));
        Game auction = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Auction not found"));
       User user=userRepository.findById(userId).orElseThrow();
//        User user = new User();
//        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setGameList(Collections.singletonList(auction));
        // Add the user to the auction's list of participants
        auction.getUser().add(user);
        club.getUserList().add(user);
        clubRepository.save(club);
    }
}






