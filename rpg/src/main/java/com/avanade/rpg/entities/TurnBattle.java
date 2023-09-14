package com.avanade.rpg.entities;

import com.avanade.rpg.dto.history.TurnBattleHistoryResponseDTO;
import com.avanade.rpg.dto.turnbattle.TurnBattleDTO;
import com.avanade.rpg.enums.TurnStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnBattle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "attacker_id")
    private Character attacker;
    @ManyToOne(optional = true)
    @JoinColumn(name = "defender_id")
    private Character defender;
    private Integer turn;
    private Integer attack;
    private Integer defense;
    private Integer damage;
    @Enumerated(EnumType.STRING)
    private TurnStatus status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public TurnBattleDTO toTurnBattleDTO(){
        return new TurnBattleDTO(
                this.id,
                this.attacker,
                this.defender,
                this.turn,
                this.attack,
                this.defense,
                this.damage,
                this.status
        );
    }

    public TurnBattleHistoryResponseDTO turnBattleHistoryResponseDTO(){
        return new TurnBattleHistoryResponseDTO(
                this.turn,
                this.attacker.getName(),
                this.attack,
                this.defender.getName(),
                this.defense,
                this.damage
        );
    }

}
