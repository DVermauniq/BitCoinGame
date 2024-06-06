package com.rwl.Bit_coin.dto;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDto {

    private String userName;
    private String email;
    private Integer phoneNumber;
    private LocalDate dob;
    private String aadharNo;
}
