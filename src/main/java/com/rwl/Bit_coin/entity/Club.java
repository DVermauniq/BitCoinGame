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
	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
	private List<Game> gameList;
	@ManyToMany
	private List<User> userList;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
	private List<Story> storyList;

}
