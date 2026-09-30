package com.Practice.StudentManagement.Dtos;


import com.Practice.StudentManagement.Model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    private AddressResponse addressResponse;
    private DepartmentResponseDto departmentResponseDto;

    private List<CourseResponseDto> courseList=new ArrayList<>();

}
