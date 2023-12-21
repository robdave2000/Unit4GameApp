package com.project.gameCatalog.controllers;

import com.project.gameCatalog.dtos.FavoriteDto;
import com.project.gameCatalog.dtos.GameDto;
import com.project.gameCatalog.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/")
    public List<FavoriteDto> getAllFavorites()
    {
        return favoriteService.getAllFavorites();
    }

    @PostMapping("/user/add/{userId}")
    public void addFavoriteToUser(@RequestBody FavoriteDto favoriteDto, @PathVariable Long userId)
    {
        favoriteService.addFavoriteToUser(favoriteDto, userId);
    }

    //Currently error, because it claims id is null
    @PutMapping("/update")
    public void updateFavoriteById(FavoriteDto favoriteDto)
    {
        favoriteService.updateFavoriteById(favoriteDto);
    }

    @DeleteMapping("/delete/{favoriteId}")
    public void deleteFavoriteById(@PathVariable Long favoriteId)
    {
        favoriteService.deleteFavoriteById(favoriteId);
    }

    @GetMapping("/{userId}")
    public List<FavoriteDto> getFavoriteByUserId(@PathVariable Long userId)
    {
        return favoriteService.getFavoriteByUserId(userId);
    }

    @GetMapping("/games/{favoriteId}")
    public List<GameDto> getAllGamesByFavoriteId(@PathVariable Long favoriteId)
    {
        return favoriteService.getAllGamesByFavoriteId(favoriteId);
    }
}
