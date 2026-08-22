package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveTeam(Team team) {
        entityManager.persist(team);
    }

    public Team findTeamById(Long id) {
        return entityManager.find(Team.class, id);
    }

    public void updateTeam(Team team) {
        entityManager.merge(team);
    }

    public void deleteTeam(Team team) {
        entityManager.remove(team);
    }

}
