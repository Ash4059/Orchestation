package com.example.orchestation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orchestation.Entity.Team;


public interface TeamRepository extends JpaRepository<Team, Long> {

}
