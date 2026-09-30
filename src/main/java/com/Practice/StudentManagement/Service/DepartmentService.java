package com.Practice.StudentManagement.Service;

import com.Practice.StudentManagement.Dtos.DepartmentRequestDto;
import com.Practice.StudentManagement.Dtos.DepartmentResponseDto;
import com.Practice.StudentManagement.Dtos.StudentResponse;
import com.Practice.StudentManagement.Exceptions.DepartmentNotFoundException;
import com.Practice.StudentManagement.Model.Department;
import com.Practice.StudentManagement.Repository.DepartmentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    public final DepartmentRepository departmentRepository;


    public DepartmentResponseDto createDepartment(@Valid DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setDepartmentName(departmentRequestDto.getDepartmentName());
        departmentRepository.save(department);

        return mapToResponse(department);
    }

    public DepartmentResponseDto getDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Please enter correct department id"));
        return mapToResponse(department);
    }

    private DepartmentResponseDto mapToResponse(Department department) {
        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        responseDto.setId(department.getId());
        responseDto.setDepartmentName(department.getDepartmentName());

        if (department.getStudents() != null) {

            //Hibernate will find the student entity which is mapped by dept_id , and gets students by foreign key

            List<StudentResponse> studentsList = department.getStudents()
                    .stream().map(Student -> {
                        StudentResponse response = new StudentResponse();
                        response.setId(Student.getId());
                        response.setFirstName(Student.getFirstName());
                        response.setLastName(Student.getLastName());
                        response.setAge(Student.getAge());
                        response.setEmail(Student.getEmail());
                        return response;
                    }).toList();
            responseDto.setStudentsList(studentsList);
        }
        return responseDto;
    }

    public List<DepartmentResponseDto> getAllDepartments() {
        List<DepartmentResponseDto>totalDepartments=departmentRepository.findAll()
                .stream().map(this::mapToResponse)
                .toList();
        return  totalDepartments;
    }

}