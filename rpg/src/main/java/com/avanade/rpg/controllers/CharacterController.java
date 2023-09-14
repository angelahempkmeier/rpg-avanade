package com.avanade.rpg.controllers;

import com.avanade.rpg.dto.characters.CharacterEditRequestDTO;
import com.avanade.rpg.dto.characters.CharacterRequestDTO;
import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.services.CharacterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {
    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> create(@Valid @RequestBody CharacterRequestDTO dto){
        CharacterResponseDTO response = characterService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> findById(@PathVariable Long id){
        CharacterResponseDTO character = characterService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> findAll(){
        List<CharacterResponseDTO> characters = characterService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(characters);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> update(@Valid @RequestBody CharacterEditRequestDTO dto,
                                                       @PathVariable Long id){
        CharacterResponseDTO characterResponseDTO = characterService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(characterResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
