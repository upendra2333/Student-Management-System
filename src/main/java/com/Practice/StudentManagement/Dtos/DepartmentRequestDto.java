package com.Practice.StudentManagement.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {

    @NotBlank(message = "department name is required")
    @Size(max = 30 , message = "department name should not exceed 30 characters")
    private String departmentName;
}
