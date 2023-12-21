package com.project.gameCatalog.services;

import com.project.gameCatalog.dtos.GameDto;
import com.project.gameCatalog.entities.Favorite;
import com.project.gameCatalog.entities.Game;
import com.project.gameCatalog.repositories.FavoriteRepository;
import com.project.gameCatalog.repositories.GameRepository;
import com.project.gameCatalog.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    @Transactional
    public void addGame(GameDto gameDto)
    {
        Game game = new Game(gameDto);
        gameRepository.saveAndFlush(game);
    }

    @Override
    @Transactional
    public void deleteGameById(Long gameId)
    {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        gameOptional.ifPresent(game -> gameRepository.delete(game));
    }

    @Override
    @Transactional
    public void updateGameById(GameDto gameDto)
    {
        Optional<Game> gameOptional = gameRepository.findById(gameDto.getId());
        gameOptional.ifPresent(game -> {
            game.setName(gameDto.getName());
            game.setCategory(gameDto.getCategory());
            game.setPublisher(gameDto.getPublisher());
            game.setSequel(gameDto.getSequel());
            game.setSequelTo(gameDto.getSequelTo());
            gameRepository.saveAndFlush(game);
        });
    }

    @Override
    public List<GameDto> getAllGames()
    {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream().map(game -> new GameDto(game)).collect(Collectors.toList());
    }

    /*
    The one with userId should be used, kept just incase
    @Override
    @Transactional
    public String addGameToFavorite(Long gameId, String favoriteName)
    {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        Optional<Favorite> favoriteOptional = favoriteRepository.findFavoriteByName(favoriteName);

        if (gameOptional.isPresent() && favoriteOptional.isPresent())
        {
            favoriteOptional.get().getGameSet().add(gameOptional.get());
            gameOptional.get().getFavoriteSet().add(favoriteOptional.get());
            gameRepository.saveAndFlush(gameOptional.get());
            favoriteRepository.saveAndFlush(favoriteOptional.get());
            return "Game added to Favorite successfully";
        }

        return "Unable to process";
    }
     */

    @Override
    @Transactional
    public String addGameToFavorite(Long gameId, String favoriteName, Long userId)
    {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        Optional<Favorite> favoriteOptional = favoriteRepository.findFavoriteByNameAndUserId(favoriteName, userId);

        if (gameOptional.isPresent() && favoriteOptional.isPresent())
        {
            favoriteOptional.get().getGameSet().add(gameOptional.get());
            gameOptional.get().getFavoriteSet().add(favoriteOptional.get());
            gameRepository.saveAndFlush(gameOptional.get());
            favoriteRepository.saveAndFlush(favoriteOptional.get());
            return "Game added to Favorite successfully";
        }

        return "Unable to process";
    }
}
