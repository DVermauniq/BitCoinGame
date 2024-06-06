package com.rwl.Bit_coin.entity;

import com.rwl.Bit_coin.enumm.GameDuration;
import com.rwl.Bit_coin.enumm.GameStatus;
import com.rwl.Bit_coin.enumm.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long gameId;

	@Enumerated(value = EnumType.STRING)
	private GameType gameType;

	private Long numberOfPlayers;
	private Double totalAmountCollected;
	private Double amountPerPerson;

	@Enumerated(value = EnumType.STRING)
	private GameDuration gameDuration;

	@Enumerated(value = EnumType.STRING)
	private GameStatus gameStatus;

	private List<Long> winnerListByOrder;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_game", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users; // Corrected mappedBy attribute

	@ManyToMany(mappedBy = "gameList", cascade = CascadeType.ALL)
	private List<Club> clubList;
}