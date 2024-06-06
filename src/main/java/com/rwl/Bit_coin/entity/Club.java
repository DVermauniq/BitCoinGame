package com.rwl.Bit_coin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long clubId;
    private String clubName;
    @ManyToMany(mappedBy = "clubList",cascade = CascadeType.ALL)
    private List<User> userList;
    @ManyToMany(mappedBy = "clubList",cascade = CascadeType.ALL)
    private List<Game> gameList;
}
