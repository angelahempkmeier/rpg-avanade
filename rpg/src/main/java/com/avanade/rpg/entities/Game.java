package com.avanade.rpg.entities;

import com.avanade.rpg.dto.history.GameHistoryResponseDTO;
import com.avanade.rpg.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Character player;
    @ManyToOne
    @JoinColumn(name = "opponent_id")
    private Character opponent;
    private Boolean playerInitiated;
    @JsonManagedReference
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TurnBattle> turnBattles = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "battle_history_id")
    private BattleHistory battleHistory;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;
    private String winner;

    public void setTurnBattles(TurnBattle turnBattle) {
        turnBattles.add(turnBattle);
    }

    public GameHistoryResponseDTO toGameHistoryResponseDTO(){
        return new GameHistoryResponseDTO(
                this.id,
                this.getPlayer().toCharacterHistoryDTO(),
                this.getOpponent().toCharacterHistoryDTO(),
                this.playerInitiated
        );
    }
}
