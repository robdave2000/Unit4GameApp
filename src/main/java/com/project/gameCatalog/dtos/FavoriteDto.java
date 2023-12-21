package com.project.gameCatalog.dtos;


import com.project.gameCatalog.entities.Favorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private Long id;

    private String name;

    private UserDto userDto;

    private Set<GameDto> gameDtoSet = new HashSet<>();

    public FavoriteDto(Favorite favorite)
    {
        if (favorite.getId() != null)
        {
            this.id = favorite.getId();
        }
        if (favorite.getName() != null)
        {
            this.name = favorite.getName();
        }
    }
}
