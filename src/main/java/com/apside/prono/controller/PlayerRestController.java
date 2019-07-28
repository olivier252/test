package com.apside.prono.controller;


import com.apside.prono.errors.InvalidPlayerDataException;
import com.apside.prono.errors.PlayerUnknownException;
import com.apside.prono.model.Player;
import com.apside.prono.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@CrossOrigin
@RestController
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(produces = "application/json", path = "/player")
    public Iterable<Player> getAllPlayers() {

        return playerService.getAllPlayers();
    }

    @GetMapping(produces = "application/json", path = "/player/{id}")
    public Player getPlayerById(@PathVariable Long id) throws PlayerUnknownException {
        Player findPlayer = new Player();
        try {
            findPlayer = playerService.getPlayerById(id);
        } catch (PlayerUnknownException e) {
            e.getMessage();
        }
        return findPlayer;
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/player")
    public Player createPlayer(@RequestBody Player player) throws InvalidPlayerDataException {
        if (player == null) {
            throw new InvalidPlayerDataException();
        } else {
            return playerService.createPlayer(player);
        }
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/player")
    public Player updatePlayer(@RequestBody Player player) {
        if (player == null) {
            throw new InvalidPlayerDataException();
        } else {
            return playerService.updatePlayer(player);
        }
    }

    @DeleteMapping(path = "/player/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
