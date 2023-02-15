package com.example.HayEquipo.repository;

import com.example.HayEquipo.entities.Player;
import com.example.HayEquipo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findPlayerByNick(String nick);

}
