package com.Practice.StudentManagement.Dtos;

import com.Practice.StudentManagement.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private String city;
    private String State;
    private String country;
}
