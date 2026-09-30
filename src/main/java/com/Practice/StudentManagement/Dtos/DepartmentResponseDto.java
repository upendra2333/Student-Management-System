package com.Practice.StudentManagement.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDto {

    private Long id;
    private String departmentName;
    private List<StudentResponse> studentsList;
}
