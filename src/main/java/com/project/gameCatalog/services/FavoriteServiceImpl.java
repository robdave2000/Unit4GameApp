package com.project.gameCatalog.services;

import com.project.gameCatalog.dtos.FavoriteDto;
import com.project.gameCatalog.dtos.GameDto;
import com.project.gameCatalog.entities.Favorite;
import com.project.gameCatalog.entities.Game;
import com.project.gameCatalog.entities.User;
import com.project.gameCatalog.repositories.FavoriteRepository;
import com.project.gameCatalog.repositories.GameRepository;
import com.project.gameCatalog.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional
    public void addFavoriteToUser(FavoriteDto favoriteDto, Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        Favorite favorite = new Favorite(favoriteDto);
        userOptional.ifPresent(favorite::setUser);
        favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    @Transactional
    public void updateFavoriteById(FavoriteDto favoriteDto)
    {
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(favoriteDto.getId());
        favoriteOptional.ifPresent(favorite -> {
            favorite.setName(favoriteDto.getName());
            favoriteRepository.saveAndFlush(favorite);
        });
    }

    @Override
    @Transactional
    public void deleteFavoriteById(Long favoriteId)
    {
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(favoriteId);
        favoriteOptional.ifPresent(favorite -> favoriteRepository.delete(favorite));
    }

    @Override
    public List<FavoriteDto> getFavoriteByUserId(Long userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent())
        {
            List<Favorite> favoriteList = favoriteRepository.findAllByUserEquals(userOptional.get());
            return favoriteList.stream().map(favorite -> new FavoriteDto(favorite)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<FavoriteDto> getAllFavorites()
    {
        List<Favorite> favoriteList = favoriteRepository.findAll();
        return favoriteList.stream().map(favorite -> new FavoriteDto(favorite)).collect(Collectors.toList());
    }

    @Override
    public List<GameDto> getAllGamesByFavoriteId(Long favoriteId)
    {
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(favoriteId);
        if (favoriteOptional.isPresent())
        {
            List<Game> gameList = gameRepository.findAllByFavoriteSetId(favoriteId);
            return gameList.stream().map(game -> new GameDto(game)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
