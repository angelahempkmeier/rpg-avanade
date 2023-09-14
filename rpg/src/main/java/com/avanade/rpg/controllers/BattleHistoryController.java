package com.avanade.rpg.controllers;

import com.avanade.rpg.dto.history.BattleHistoryResponseDTO;
import com.avanade.rpg.services.BattleHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("history")
public class BattleHistoryController {

    private BattleHistoryService battleHistoryService;

    public BattleHistoryController(BattleHistoryService battleHistoryService) {
        this.battleHistoryService = battleHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<BattleHistoryResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(battleHistoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BattleHistoryResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(battleHistoryService.findById(id));
    }
}
