package com.project.gameCatalog.entities;

import com.project.gameCatalog.dtos.GameDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String category;

    @Column
    private String publisher;

    @Column
    private Boolean sequel;

    @Column(nullable = true)
    private String sequelTo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "GameFavorite",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Favorite> favoriteSet = new HashSet<>();

    public Game(GameDto gameDto)
    {
        if (gameDto.getName() != null)
        {
            this.name = gameDto.getName();
        }
        if (gameDto.getCategory() != null)
        {
            this.category = gameDto.getCategory();
        }
        if (gameDto.getPublisher() != null)
        {
            this.publisher = gameDto.getPublisher();
        }
        if (gameDto.getSequel() != null)
        {
            this.sequel = gameDto.getSequel();
        }
        if (gameDto.getSequelTo() != null)
        {
            this.sequelTo = gameDto.getSequelTo();
        }
    }
}
