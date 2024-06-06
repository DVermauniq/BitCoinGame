package com.rwl.Bit_coin.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignupRequest {

    @Size(min = 3, max = 20)
    private String firstName;
    @Size(min = 3, max = 20)
    private String lastName;
    @Size() // Assuming phone number is 10 digits
    private Integer phoneNumber;
    @Size(max = 50)
    @Email
    private String email;
    @Size(max = 120)
    private String password;


    public SignupRequest() {
    }

    //removed not blank for all arguments
    public SignupRequest(@Size(min = 3, max = 20) String firstName, @Size(min = 3, max = 20) String lastName,
                         @Size(max = 10) Integer phoneNumber, @Size(max = 50) @Email String email,
                         @Size(min = 6, max = 40) String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
