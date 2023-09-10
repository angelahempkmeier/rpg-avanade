package com.avanade.rpg.entities;

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
    private Integer turn;
    private Integer attack;
    private Integer defense;
    private Integer damage;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
