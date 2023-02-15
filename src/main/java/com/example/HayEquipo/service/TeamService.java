package com.example.HayEquipo.service;

import com.example.HayEquipo.entities.Player;
import com.example.HayEquipo.entities.Team;
import com.example.HayEquipo.exceptions.BadRequestException;
import com.example.HayEquipo.exceptions.ResourceNotFoundException;
import com.example.HayEquipo.repository.PlayerRepository;
import com.example.HayEquipo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;

    public Team createTeam(Team team) throws BadRequestException {
        Optional<Team> findTeam = teamRepository.findTeamByName(team.getName());
        if (!findTeam.isPresent()){
            team.setElo(1000);
            return teamRepository.save(team);
        }else {
            throw new BadRequestException("Ya existe un equipo con el nombre: " + team.getName());
        }

    }
    public Team getTeamById(Long id) throws ResourceNotFoundException {
        Optional<Team> findTeamById = teamRepository.findById(id);
        if (findTeamById.isPresent()){
            return findTeamById.get();
        }else {
            throw new ResourceNotFoundException("No existe el team con id: " + id);
        }
    }
    public String deleteTeam(Long id) throws ResourceNotFoundException {
        Team findTeamById = getTeamById(id);
        teamRepository.deleteById(id);
        return "El team con id: " + id + " se ha eliminado con éxito";
    }
    public Team addPlayerToTeam(Long playerId, Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        Player player = playerRepository.findById(playerId).get();

        team.getPlayers().add(player);
        player.setTeam(team);

        playerRepository.save(player);
        return teamRepository.save(team);
    }
    public List<Team> findAllTeams(){
        return teamRepository.findAllWithJugadores();
    }

}
