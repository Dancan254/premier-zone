package com.mongs.premierzone.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    void deleteByName(String name);
    Optional<Player> findByName(String name);
    List<Player> findByNation(String nation);
    List<Player> findByPosition(String position);
}
