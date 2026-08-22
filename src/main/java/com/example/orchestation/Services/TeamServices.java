package com.example.orchestation.Services;

import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Team;
import com.example.orchestation.Repository.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class TeamServices {

    private final TeamRepository teamRepository;

    public TeamServices(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void saveTeam(Team team) {
        teamRepository.saveTeam(team);
    }

    public Team findTeamById(Long id) {
        return teamRepository.findTeamById(id);
    }

    @Transactional
    public void updateTeam(Long id, Team team) {
        Team existingTeam = findTeamById(id);
        if (existingTeam != null) {
            teamRepository.updateTeam(team);
        } else {
            throw new IllegalArgumentException("Team with ID " + id + " not found.");
        }
    }

    @Transactional
    public void deleteTeam(Long id) {
        Team team = findTeamById(id);
        if (team != null) {
            teamRepository.deleteTeam(team);
        } else {
            throw new IllegalArgumentException("Team with ID " + id + " not found.");
        }
    }

}
