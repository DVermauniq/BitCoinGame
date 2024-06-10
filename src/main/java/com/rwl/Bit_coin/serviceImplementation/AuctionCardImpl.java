package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.AuctionCardDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Club;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.enumm.GameType;
import com.rwl.Bit_coin.repo.ClubRepository;
import com.rwl.Bit_coin.repo.GameRepository;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.AuctionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionCardImpl implements AuctionCardService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game createAuctionCard(AuctionCardDto auctionCardDTO, String firstName) {
        User user = userRepository.findByFirstname(firstName);
        if (user == null || !user.getPassword().equals(auctionCardDTO.getPassword())) {
            // Handle invalid user or password mismatch
            return null;
        }

        Game game = new Game();
        game.setGameType(GameType.AUCTION); // Assuming Auction is a game type
        game.setNumberOfPlayers(auctionCardDTO.getNoOfPeople().longValue());
        game.setAmountPerPerson(auctionCardDTO.getAmountPerPerson().doubleValue());
        game.setDate(auctionCardDTO.getDate());
        // Set other properties from DTO

        List<User> users = new ArrayList<>();
        users.add(user);
        game.setUser(users);

        //  user.setGames(users);

        return gameRepository.save(game);
    }

    @Override
    public Game addUserToAuctionFromClub(Long clubId, Long gameId, UserDto userDTO) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new RuntimeException("Club not found"));
        Game auction = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Auction not found"));

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setGameList(Collections.singletonList(auction));

        club.getUserList().add(user);

//        return gameRepository.save(club);
        return null;
    }
    }






