package com.example.HayEquipo.repository;

import com.example.HayEquipo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findTeamByName(String name);
    @Query("SELECT e FROM Team e LEFT JOIN FETCH e.players")
    List<Team> findAllWithJugadores();
}
