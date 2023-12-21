package com.project.gameCatalog.repositories;

import com.project.gameCatalog.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByFavoriteSetId(Long favoriteSetId);
}
