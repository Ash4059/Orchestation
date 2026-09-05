package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.CreateEmployeeRequestDto;
import com.example.orchestation.DTO.EmployeeInfoDto;
import com.example.orchestation.DTO.EmployeeResponseDto;
import com.example.orchestation.DTO.UpdateEmployeeRequestDto;
import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Entity.Role;
import com.example.orchestation.Entity.Team;
import com.example.orchestation.Repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public abstract class EmployeeMapper {

    @Autowired
    private TeamRepository teamRepository;

    @Mapping(target = "teamName", source = "team.name")
    public abstract EmployeeInfoDto toDto(Employee employee);

    @Mapping(target = "team", source = "teamId")
    public abstract Employee toEntity(CreateEmployeeRequestDto dto);

    @Mapping(target = "team", source = "teamId")
    public abstract void updateEntityFromDto(UpdateEmployeeRequestDto dto, @MappingTarget Employee employee);

    public EmployeeResponseDto toResponseDto(Employee employee, String message) {
        return new EmployeeResponseDto(toDto(employee), message);
    }

    protected Team mapTeamIdToTeam(Long teamId) {
        if (teamId == null) {
            return null;
        }
        return teamRepository.findTeamById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));
    }

    protected Role mapRole(String role) {
        if (role == null) {
            return null;
        }
        return Role.valueOf(role.toUpperCase());
    }
}
