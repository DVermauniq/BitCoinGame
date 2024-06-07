package com.rwl.Bit_coin.entity;

import com.rwl.Bit_coin.enumm.ClubType;
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
    @Enumerated(EnumType.STRING)
    private ClubType clubType;

    @ManyToMany(mappedBy = "clubList")
    private List<User> userList;

    @ManyToMany
    @JoinTable(
        name = "club_game",
        joinColumns = @JoinColumn(name = "club_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> gameList;
}
