package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.ClubDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Club;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.repo.ClubRepository;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Club createClub(ClubDto clubDto) {
        Club club = new Club();
        club.setClubId(clubDto.getClubId());
        club.setClubName(clubDto.getClubName());
        return clubRepository.save(club);
    }

    @Override
    public Club addUserToClub(Long clubId,Long userId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new RuntimeException("Club not found"));
       User user= userRepository.findById(userId).orElseThrow();
//        User user = new User();
//        user.setUserId(userDTO.getUserId());
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
        club.getUserList().add(user);
        return clubRepository.save(club);
    }
}
