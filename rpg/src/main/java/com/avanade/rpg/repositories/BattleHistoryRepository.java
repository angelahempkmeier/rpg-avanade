package com.avanade.rpg.repositories;

import com.avanade.rpg.entities.BattleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleHistoryRepository extends JpaRepository<BattleHistory, Long> {

}
