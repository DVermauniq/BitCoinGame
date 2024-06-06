package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.AuctionCardDto;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.enumm.GameType;
import com.rwl.Bit_coin.repo.GameRepository;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.AuctionCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionCardImpl implements AuctionCardService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game createAuctionCard(AuctionCardDto auctionCardDTO, String firstName) {
        User user = userRepository.findByUsername(firstName);
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
        game.setUsers(users);

        //  user.setGames(users);

        return gameRepository.save(game);
    }

    @Override
    public void addUserToAuction(Long gameId, Long userId) {
        Optional<Game> optionalAuction = gameRepository.findById(gameId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalAuction.isPresent() && optionalUser.isPresent()) {
            Game game = optionalAuction.get();
            User user = optionalUser.get();

            // Add the user to the auction's list of participants
            game.getUsers().add(user);

            gameRepository.save(game);
        } else {
            // Handle cases where the auction or user is not found
            throw new IllegalArgumentException("Auction or user not found");
        }
    }


    }



