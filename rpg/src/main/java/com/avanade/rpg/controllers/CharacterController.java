package com.avanade.rpg.controllers;

import com.avanade.rpg.services.CharacterService;
import com.avanade.rpg.dto.CharacterRequestDTO;
import com.avanade.rpg.dto.CharacterResponseDTO;
import com.avanade.rpg.dto.CharactersAvailableDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("characters")
public class CharacterController {

    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> create(@RequestBody CharacterRequestDTO dto){
        CharacterResponseDTO response = characterService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> findById(@PathVariable Long id){
        CharacterResponseDTO character = characterService.findById(id);
        return ResponseEntity.ok().body(character);
    }

    public CharactersAvailableDTO getCharactersAvailable(){
        return null;
    }
}
