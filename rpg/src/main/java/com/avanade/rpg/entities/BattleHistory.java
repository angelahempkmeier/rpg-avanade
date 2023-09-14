package com.avanade.rpg.entities;

import com.avanade.rpg.dto.history.BattleHistoryResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BattleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Game game;
    public BattleHistoryResponseDTO toResponseDTO(){
        return new BattleHistoryResponseDTO(
                this.id,
                this.game.toGameHistoryResponseDTO(),
                this.game.getTurnBattles().stream().map(TurnBattle::turnBattleHistoryResponseDTO).collect(Collectors.toList()),
                this.game.getWinner()
        );
    }
}
