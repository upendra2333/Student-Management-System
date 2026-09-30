package com.Practice.StudentManagement.Dtos;

import com.Practice.StudentManagement.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

    private String city;
    private String state;
    private String country;
}
