package com.example.orchestation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldErrorResponseDto extends ErrorResponseDto{

    ErrorResponseDto errorResponseDto;
    Map<String, String> fieldErrors;

}
