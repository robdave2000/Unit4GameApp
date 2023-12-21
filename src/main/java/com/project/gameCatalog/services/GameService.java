package com.project.gameCatalog.services;

import com.project.gameCatalog.dtos.GameDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface GameService {
    @Transactional
    void addGame(GameDto gameDto);

    @Transactional
    void deleteGameById(Long gameId);

    @Transactional
    void updateGameById(GameDto gameDto);

    List<GameDto> getAllGames();

    /*
    The one with userId should be used, kept just incase
    @Transactional
    String addGameToFavorite(Long gameId, String favoriteName);
     */

    @Transactional
    String addGameToFavorite(Long gameId, String favoriteName, Long userId);
}
