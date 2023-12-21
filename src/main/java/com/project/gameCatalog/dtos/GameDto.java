package com.project.gameCatalog.dtos;

import com.project.gameCatalog.entities.Favorite;
import com.project.gameCatalog.entities.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private Long id;

    private String name;

    private String category;

    private String publisher;

    private Boolean sequel;

    private String sequelTo;

    private Set<FavoriteDto> favoriteDtoSet = new HashSet<>();

    public GameDto(Game game)
    {
        if (game.getId() != null)
        {
            this.id = game.getId();
        }
        if (game.getName() != null)
        {
            this.name = game.getName();
        }
        if (game.getCategory() != null)
        {
            this.category = game.getCategory();
        }
        if (game.getPublisher() != null)
        {
            this.publisher = game.getPublisher();
        }
        if (game.getSequel() != null)
        {
            this.sequel = game.getSequel();
        }
        if (game.getSequelTo() != null)
        {
            this.sequelTo = game.getSequelTo();
        }
    }
}
