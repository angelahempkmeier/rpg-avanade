package com.avanade.rpg.controllers;

import com.avanade.rpg.dto.game.AttackDTO;
import com.avanade.rpg.dto.game.DefenseDTO;
import com.avanade.rpg.dto.game.StartGameDTO;
import com.avanade.rpg.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("game")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startGame(@RequestBody StartGameDTO startGameDTO){
        Map<String, Object> game = gameService.startGame(startGameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @PostMapping("/attack")
    public ResponseEntity<Map<String, Object>> attack(@RequestBody AttackDTO id){
        Map<String, Object> response = gameService.attack(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/defense")
    public ResponseEntity<Map<String, Object>> defense(@RequestBody DefenseDTO id){
        Map<String, Object> response = gameService.defense(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/damage")
    public ResponseEntity<Map<String, Object>> damage(){
        return ResponseEntity.ok(gameService.damage());
    }


}
