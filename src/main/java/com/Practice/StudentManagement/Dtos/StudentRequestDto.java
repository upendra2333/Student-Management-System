package com.Practice.StudentManagement.Dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotBlank(message = "please enter your firstName")
    private String firstName;
    @NotBlank(message = "please enter your lastName")
    private String lastName;
    @NotNull(message = "Enter your age")
    private Integer age;

    @NotBlank( message = "please enter email" )
    @Email(message = "Enter your personal email")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])(?=.*[@#$!%^&*]).*$" )
    private String passWord;

    @NotNull(message = "enter your address details")
    @Valid
    private AddressRequestDto addressDto;

    @NotNull(message = "enter your department id ")
    private Long department_id;

    //student need to enroll the course id's which are already present in the Course db
    private List<Long> coursesOfStudent=new ArrayList<>();
}
