package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveTeam(Team team) {
        entityManager.persist(team);
    }

    public Optional<Team> findTeamById(Long id) {
        return Optional.ofNullable(entityManager.find(Team.class, id));
    }

    public void deleteTeam(Team team) {
        entityManager.remove(team);
    }

}
