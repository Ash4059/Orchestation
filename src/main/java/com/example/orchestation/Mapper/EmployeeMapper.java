package com.example.orchestation.Mapper;

import com.example.orchestation.DTO.CreateEmployeeRequestDto;
import com.example.orchestation.DTO.EmployeeInfoDto;
import com.example.orchestation.DTO.UpdateEmployeeRequestDto;
import com.example.orchestation.Entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeInfoDto toDto(Employee employee);

    Employee toEntity(CreateEmployeeRequestDto dto);

    Employee toEntity(UpdateEmployeeRequestDto dto);

}
