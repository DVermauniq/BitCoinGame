package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.dtos.ClubDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Club;
import com.rwl.Bit_coin.serviceImplementation.ClubImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubImpl clubimpl;

    @PostMapping("/create-club")
     public Club createClub(ClubDto clubDto){
         return clubimpl.createClub(clubDto);
     }

     @PostMapping("/user-add-club")
     public Club addUsertoClub(@RequestParam Long userId,@RequestParam Long clubId ){
        return clubimpl.addUserToClub(clubId,userId);

     }
}
