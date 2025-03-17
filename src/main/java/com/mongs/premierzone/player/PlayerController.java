package com.mongs.premierzone.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Player>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String nation,
            @RequestParam(required = false) String position
    ) {
       if (team != null) {
           return ResponseEntity.ok(playerService.findPlayersFromTeam(team));
       }
         if (name != null) {
              return ResponseEntity.ok(playerService.getPlayersByName(name));
         }
         if (nation != null) {
                return ResponseEntity.ok(playerService.getPlayersByNation(nation));
            }
         if (position != null) {
             return ResponseEntity.ok(playerService.getPlayersByPosition(position));
         }
         return ResponseEntity.ok(playerService.getPlayers());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.addNewPlayer(player), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player result = playerService.updatePlayer(player);
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deletePlayer(@PathVariable String name) {
        playerService.deletePlayer(name);
        return new ResponseEntity<>("Player deleted", HttpStatus.OK);
    }
}
