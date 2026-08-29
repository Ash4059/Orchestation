package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.CreateEmployeeRequestDto;
import com.example.orchestation.DTO.EmployeeInfoDto;
import com.example.orchestation.DTO.EmployeeResponseDto;
import com.example.orchestation.DTO.UpdateEmployeeRequestDto;
import com.example.orchestation.Entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface EmployeeMapper {

    EmployeeInfoDto toDto(Employee employee);

    Employee toEntity(CreateEmployeeRequestDto dto);

    void updateEntityFromDto(UpdateEmployeeRequestDto dto, @MappingTarget Employee employee);

    default EmployeeResponseDto toResponseDto(Employee employee, String message) {
        return new EmployeeResponseDto(toDto(employee), message);
    }
}
