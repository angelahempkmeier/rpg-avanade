package com.avanade.rpg.services;

import com.avanade.rpg.dto.history.BattleHistoryResponseDTO;
import com.avanade.rpg.entities.BattleHistory;
import com.avanade.rpg.exceptions.HistoryDoesntExistsException;
import com.avanade.rpg.repositories.BattleHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BattleHistoryService {

    private BattleHistoryRepository battleHistoryRepository;

    public BattleHistoryService(BattleHistoryRepository battleHistoryRepository) {
        this.battleHistoryRepository = battleHistoryRepository;
    }

    public List<BattleHistoryResponseDTO> findAll(){
        return battleHistoryRepository.findAll()
                .stream()
                .map(BattleHistory::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BattleHistoryResponseDTO findById(Long id){
        return battleHistoryRepository.findById(id).orElseThrow(
                () -> new HistoryDoesntExistsException(id)).toResponseDTO();
    }
}
