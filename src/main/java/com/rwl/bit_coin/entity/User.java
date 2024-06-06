package com.rwl.bit_coin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    @Size(max = 15)
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Size(max = 50)
    @Email
    private String userEmail;
    @Size(max = 120)
    private String password;
}
