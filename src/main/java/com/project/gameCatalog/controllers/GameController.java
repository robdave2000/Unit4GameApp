package com.project.gameCatalog.controllers;

import com.project.gameCatalog.dtos.GameDto;
import com.project.gameCatalog.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public List<GameDto> getAllGames()
    {
        return gameService.getAllGames();
    }

    @PostMapping("/add")
    public void addGame(@RequestBody GameDto gameDto)
    {
        gameService.addGame(gameDto);
    }

    @DeleteMapping("/delete/{gameId}")
    public void deleteGameById(@PathVariable Long gameId)
    {
        gameService.deleteGameById(gameId);
    }

    @PutMapping("/update")
    public void updateGameById(@RequestBody GameDto gameDto)
    {
        gameService.updateGameById(gameDto);
    }

    //Stack overflow error
    @PostMapping("/favorite/add/{gameId}/{favoriteName}/{userId}")
    public String addGameToFavorite(@PathVariable Long gameId, @PathVariable String favoriteName, @PathVariable Long userId)
    {
        return gameService.addGameToFavorite(gameId, favoriteName, userId);
    }

    /*
    The one with the userId should be used, kept this one just incase
    @PostMapping("/favorite/add/{gameId}/{favoriteName}")
    public String addGameToFavorite(@PathVariable Long gameId, @PathVariable String favoriteName)
    {
        return gameService.addGameToFavorite(gameId, favoriteName);
    }
     */
}
