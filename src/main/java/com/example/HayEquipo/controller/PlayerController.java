package com.example.HayEquipo.controller;

import com.example.HayEquipo.entities.Player;
import com.example.HayEquipo.exceptions.BadRequestException;
import com.example.HayEquipo.exceptions.ResourceNotFoundException;
import com.example.HayEquipo.service.PlayerService;
import io.micrometer.common.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @PostMapping
    private ResponseEntity<Player> createPlayer(@RequestBody @Nullable Player player) throws BadRequestException {
        return ResponseEntity.ok(playerService.createPlayer(player));
    }
    @GetMapping("/{id}")
    ResponseEntity<Player> getPlayerById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }
    @GetMapping("/nick/{nick}")
    ResponseEntity<Player> getPlayerByName(@PathVariable String nick) throws ResourceNotFoundException {
        return ResponseEntity.ok(playerService.getPlayerByNick(nick));
    }
    @PutMapping
    ResponseEntity<Player> updatePlayer(@RequestBody Player player) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(playerService.putPlayer(player));
    }
}
