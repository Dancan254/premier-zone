package com.mongs.premierzone.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    ///  get players ///
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    /// get players from a team ///
    public List<Player> findPlayersFromTeam(String teamName){
        return playerRepository.findAll().stream()
                .filter(player -> teamName.equals(player.getTeam()))
                .toList();
    }

    /// Get players by a certain name ///
    public List<Player> getPlayersByName(String name){
        return playerRepository.findAll().stream()
                .filter(player -> name.toLowerCase().contains(player.getName().toLowerCase()))
                .toList();
    }
    /// Get players by a certain nation ///
    /// just discovered that using the findByNation method in the PlayerRepository interface is more efficient
    /// reason being that it uses less resources and narrows down the query to only the required data instead of finding all then filtering using streams///
    public List<Player> getPlayersByNation(String nation){
        return playerRepository.findByNation(nation);
    }

    /// Add player ///
    public Player addNewPlayer(Player player) {
        return playerRepository.save(player);
    }

    /// update player ///
    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName());
        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPos(updatedPlayer.getPos());
            playerToUpdate.setNation(updatedPlayer.getNation());
            playerRepository.save(playerToUpdate);
            return playerRepository.save(updatedPlayer);
        } else {
            return null;
        }
    }

    /// delete player ///
    @Transactional
    public void deletePlayer(String name) {
        playerRepository.deleteByName(name);
    }

    public List<Player> getPlayersByPosition(String position) {
        return playerRepository.findByPosition(position);
    }
}
