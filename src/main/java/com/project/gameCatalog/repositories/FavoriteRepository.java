package com.project.gameCatalog.repositories;

import com.project.gameCatalog.entities.Favorite;
import com.project.gameCatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findFavoriteByName(String name);

    List<Favorite> findAllByUserEquals(User user);

    Optional<Favorite> findFavoriteByNameAndUserId(String name, Long userId);
}
