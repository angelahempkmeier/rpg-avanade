package com.avanade.rpg.entities;

import com.avanade.rpg.enums.TurnStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
