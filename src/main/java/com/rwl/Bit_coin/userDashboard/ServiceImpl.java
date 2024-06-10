package com.rwl.Bit_coin.userDashboard;

import com.rwl.Bit_coin.dtos.UserGameDto;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.enumm.ClubType;
import com.rwl.Bit_coin.enumm.GameStatus;
import com.rwl.Bit_coin.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements ServiceInterface {

    @Autowired
    UserRepository userRepo;
    @Override
    public List<List<UserGameDto>> findGamesByUserId(Long userId) {
        List<Game> gameList = userRepo.findById(userId).get().getGameList();

        List<UserGameDto> gamesBySystemGeneratedClubs = new ArrayList<>();
        List<UserGameDto> gamesByOwnGeneratedGamesClubs = new ArrayList<>();

        for (Game game : gameList) {

            UserGameDto userGameDto = new UserGameDto();
            userGameDto.setGameId(game.getGameId());
            userGameDto.setClubType(game.getClub().getClubType());
            userGameDto.setNumberOfPlayers(game.getNumberOfPlayers());
            userGameDto.setTotalAmountCollected(game.getTotalAmountCollected());
            userGameDto.setStartDate(game.getStartDate());

            if (game.getClub().getClubType().equals(ClubType.OWN_CLUB)){
                gamesByOwnGeneratedGamesClubs.add(userGameDto);
            } else if (game.getClub().getClubType().equals(ClubType.SYSTEM_GENERATED)) {
                gamesBySystemGeneratedClubs.add(userGameDto);
            }
        }

        List<List<UserGameDto>> resultList = new ArrayList<>();
        resultList.add(gamesByOwnGeneratedGamesClubs);
        resultList.add(gamesBySystemGeneratedClubs);
        return resultList;
    }

    @Override
    public Integer countCompletedBcByUserId(Long userId) {
        List<Game> gameList = userRepo.findById(userId).get().getGameList();
        int count = 0;
        for (Game game : gameList) {
            if(game.getGameStatus().equals(GameStatus.COMPLETED)){
                count++;
            }
        }
        return count;
    }
}
