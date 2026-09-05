package com.example.orchestation.Services;

import com.example.orchestation.DTO.CreateTeamRequestDto;
import com.example.orchestation.DTO.TeamInfoDto;
import com.example.orchestation.DTO.TeamResponseDto;
import com.example.orchestation.DTO.UpdateTeamRequestDto;
import com.example.orchestation.Mapper.TeamMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Team;
import com.example.orchestation.Repository.TeamRepository;

import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TeamServices {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    private Team getTeamById(Long Id){
        return teamRepository.findTeamById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with Id : " + Id));
    }

    @Transactional
    public TeamResponseDto saveTeam(CreateTeamRequestDto teamRequestDto) {
        Team team = teamMapper.toEntity(teamRequestDto);
        teamRepository.saveTeam(team);
        String message = String.format("Team with ID: %s, created successfully!!!", team.getId());
        return teamMapper.toResponseDto(team, message);
    }

    public TeamInfoDto findTeamById(Long id) {
        Team team = getTeamById(id);
        return teamMapper.toDto(team);
    }

    @Transactional
    public TeamResponseDto updateTeam(Long id, UpdateTeamRequestDto dto) {
        Team team = getTeamById(id);
        teamMapper.updateEntityFromDto(dto, team);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Team with ID %d updated successfully", id);
        return teamMapper.toResponseDto(team, message);
    }

    @Transactional
    public TeamResponseDto deleteTeam(Long id) {
        Team team = getTeamById(id);
        teamRepository.deleteTeam(team);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Team with ID %d deleted successfully", id);
        return teamMapper.toResponseDto(team, message);
    }

}
