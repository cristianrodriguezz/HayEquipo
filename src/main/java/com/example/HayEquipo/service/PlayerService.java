package com.example.HayEquipo.service;

import com.example.HayEquipo.entities.Player;
import com.example.HayEquipo.entities.Team;
import com.example.HayEquipo.exceptions.BadRequestException;
import com.example.HayEquipo.exceptions.ResourceNotFoundException;
import com.example.HayEquipo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    public Player createPlayer(Player player) throws BadRequestException {
        Optional<Player> findPlayer = playerRepository.findPlayerByNick(player.getNick());
        if (findPlayer.isPresent()){
            throw new BadRequestException("Ya existe un player con ese nick");
        }else {
            return playerRepository.save(player);
        }
    }
    public Player getPlayerById(Long id) throws ResourceNotFoundException {
        Optional<Player> findPlayer = playerRepository.findById(id);
        if (findPlayer.isPresent()){
            return findPlayer.get();
        }else {
            throw new ResourceNotFoundException("No existe un player con el id: " + id);
        }
    }
    public Player getPlayerByNick(String nick) throws ResourceNotFoundException {
        Optional<Player> findPlayer = playerRepository.findPlayerByNick(nick);
        if (findPlayer.isPresent()){
            return findPlayer.get();
        }else {
            throw new ResourceNotFoundException("No existe el player con nick: " + nick);
        }
    }
    public Player putPlayer(Player player) throws BadRequestException, ResourceNotFoundException {
        Optional<Player> findPlayer = playerRepository.findById(player.getId());
        if (findPlayer.isPresent()){
            return createPlayer(player);
        }else {
            throw new ResourceNotFoundException("No existe el player con id: " + player.getId());
        }
    }
}
