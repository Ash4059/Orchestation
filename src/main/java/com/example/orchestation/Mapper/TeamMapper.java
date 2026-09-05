package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.*;
import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Entity.Team;
import com.example.orchestation.Entity.Workspace;
import com.example.orchestation.Repository.WorkspaceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TeamMapper {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Mapping(target = "workSpaceName", source = "workspace.name")
    @Mapping(target = "employeeNames", source = "employees")
    public abstract TeamInfoDto toDto(Team team);

    @Mapping(target = "workspace", source = "workspaceId")
    public abstract Team toEntity(CreateTeamRequestDto dto);

    public abstract Team toEntity(UpdateTeamRequestDto dto);

    public abstract void updateEntityFromDto(UpdateTeamRequestDto dto, @MappingTarget Team team);

    public TeamResponseDto toResponseDto(Team team, String message) {
        return new TeamResponseDto(toDto(team), message);
    }

    public Workspace mapWorkspaceIdToWorkspace(Long workspaceId){
        if(workspaceId == null){
            return  null;
        }
        return workspaceRepository.findWorkspaceById(workspaceId)
                .get();
    }

    public String mapEmployeeToEmployeeName(Employee employee){
        if(employee == null){
            return "";
        }
        return employee.getName();
    }

}
