package com.rwl.Bit_coin.repo;


import java.time.LocalDateTime;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rwl.Bit_coin.entity.Story;


@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

//	List<Status> findByPlayerId(Long playerId);
//
	void deleteByCreatedAtBefore(LocalDateTime dateTime);
	
	



}
