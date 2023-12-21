package com.project.gameCatalog.services;

import com.project.gameCatalog.dtos.FavoriteDto;
import com.project.gameCatalog.dtos.GameDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface FavoriteService {
    @Transactional
    void addFavoriteToUser(FavoriteDto favoriteDto, Long userId);

    @Transactional
    void updateFavoriteById(FavoriteDto favoriteDto);

    @Transactional
    void deleteFavoriteById(Long favoriteId);

    List<FavoriteDto> getFavoriteByUserId(Long userId);

    List<FavoriteDto> getAllFavorites();

    List<GameDto> getAllGamesByFavoriteId(Long favoriteId);
}
