package com.example.orchestation.Controller;

import com.example.orchestation.DTO.CreateTeamRequestDto;
import com.example.orchestation.DTO.TeamInfoDto;
import com.example.orchestation.DTO.TeamResponseDto;
import com.example.orchestation.DTO.UpdateTeamRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestation.Entity.Team;
import com.example.orchestation.Services.TeamServices;

@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamServices teamServices;


    @PostMapping
    public ResponseEntity<?> saveTeam(@RequestBody CreateTeamRequestDto createTeamRequestDto) {
        TeamResponseDto dto = teamServices.saveTeam(createTeamRequestDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTeamById(@PathVariable Long id) {
        TeamInfoDto teamInfoDto = teamServices.findTeamById(id);
        return ResponseEntity.ok(teamInfoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody UpdateTeamRequestDto updateTeamRequestDto) {
        TeamResponseDto teamResponseDto = teamServices.updateTeam(id, updateTeamRequestDto);
        return ResponseEntity.ok(teamResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        TeamResponseDto teamResponseDto = teamServices.deleteTeam(id);
        return ResponseEntity.ok(teamResponseDto);
    }

}
