package com.avanade.rpg.repositories;

import com.avanade.rpg.entities.TurnBattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnBattleRepository extends JpaRepository<TurnBattle, Long> {
}
