package com.Practice.StudentManagement.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {

    @NotBlank(message = "courseName is required")
    private String courseName;
    @NotNull(message = "fees is required")
    private Double fees;
}
