package com.project.gameCatalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.gameCatalog.dtos.FavoriteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToMany(mappedBy = "favoriteSet", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Game> gameSet = new HashSet<>();

    public Favorite(FavoriteDto favoriteDto)
    {
        if (favoriteDto.getName() != null)
        {
            this.name = favoriteDto.getName();
        }
    }
}
