package com.avanade.rpg.entities;

import com.avanade.rpg.enums.GameStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Character player;
    @OneToOne
    private Character opponent;
    private Boolean playerInitiated;
    @OneToMany(mappedBy = "game")
    List<TurnBattle> turnBattles = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "battle_history_id")
    private BattleHistory battleHistory;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    public void setTurnBattles(TurnBattle turnBattle) {
        turnBattles.add(turnBattle);
    }
}
