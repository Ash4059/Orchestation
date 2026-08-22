package com.example.orchestation.Controller;

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

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamServices teamServices;

    public TeamController(TeamServices teamServices) {
        this.teamServices = teamServices;
    }

    @PostMapping
    public ResponseEntity<?> saveTeam(@RequestBody Team team) {
        teamServices.saveTeam(team);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTeamById(@PathVariable Long id) {
        Team team = teamServices.findTeamById(id);
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        teamServices.updateTeam(id, team);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamServices.deleteTeam(id);
        return ResponseEntity.ok().build();
    }

}
