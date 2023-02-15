package com.example.HayEquipo.controller;

import com.example.HayEquipo.entities.Team;
import com.example.HayEquipo.exceptions.BadRequestException;
import com.example.HayEquipo.exceptions.ResourceNotFoundException;
import com.example.HayEquipo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    TeamService teamService;
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) throws BadRequestException {
        return ResponseEntity.ok(teamService.createTeam(team));
    }
    @PostMapping("/team/{teamId}/player/{playerId}")
    public ResponseEntity<Team> addPlayerToTeam(@PathVariable Long playerId, @PathVariable Long teamId){
        return ResponseEntity.ok(teamService.addPlayerToTeam(playerId,teamId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Team> findTeamById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(teamService.deleteTeam(id));
    }
    @GetMapping
    public ResponseEntity<List<Team>> findAllTeams(){
        return ResponseEntity.ok(teamService.findAllTeams());
    }
}
