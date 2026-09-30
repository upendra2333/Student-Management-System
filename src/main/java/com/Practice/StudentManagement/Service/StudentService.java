package com.Practice.StudentManagement.Service;


import com.Practice.StudentManagement.Dtos.*;
import com.Practice.StudentManagement.Exceptions.CourseNotFoundException;
import com.Practice.StudentManagement.Exceptions.DepartmentNotFoundException;
import com.Practice.StudentManagement.Exceptions.StudentNotFoundException;
import com.Practice.StudentManagement.Model.Address;
import com.Practice.StudentManagement.Model.Course;
import com.Practice.StudentManagement.Model.Department;
import com.Practice.StudentManagement.Model.Student;
import com.Practice.StudentManagement.Repository.CourseRepository;
import com.Practice.StudentManagement.Repository.DepartmentRepository;
import com.Practice.StudentManagement.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;

    public  StudentResponse createStudent(@Valid StudentRequestDto requestDto) {
        Student student=new Student();
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());
        student.setAge(requestDto.getAge());
        student.setEmail(requestDto.getEmail());
        student.setPassWord(requestDto.getPassWord());

        //Address entity
        Address address=new Address();
        address.setCity(requestDto.getAddressDto().getCity());
        address.setState(requestDto.getAddressDto().getState());
        address.setCountry(requestDto.getAddressDto().getCountry());

        student.setAddress(address);

        //Department entity
        Department department=departmentRepository.findById(requestDto.getDepartment_id())
                        .orElseThrow(()->new DepartmentNotFoundException("Department not found with this id "+requestDto.getDepartment_id()));

        student.setDepartment(department);

        //course entity
        List<Course> courseIds=courseRepository.findAllById(requestDto.getCoursesOfStudent());
        log.info("course list for student "+student.getId()+"and courses are  "+courseIds);
        student.setCourseList(courseIds);

        studentRepository.save(student);
        return mapToResponse(student);

    }

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response=new StudentResponse();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setAge(student.getAge());
        response.setEmail(student.getEmail());

        if (student.getAddress() != null) {
            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setId(student.getAddress().getId());
            addressResponse.setCity(student.getAddress().getCity());
            addressResponse.setState(student.getAddress().getState());
            addressResponse.setCountry(student.getAddress().getCountry());
            response.setAddressResponse(addressResponse);
        }

        if (student.getDepartment() != null) {
            DepartmentResponseDto responseDto = new DepartmentResponseDto();
            responseDto.setId(student.getDepartment().getId());
            responseDto.setDepartmentName(student.getDepartment().getDepartmentName());
            response.setDepartmentResponseDto(responseDto);
        }
        if(student.getCourseList()!=null){
            List<CourseResponseDto> courseResponseDtosList=student.getCourseList()
                            .stream().map(Course->{
                                CourseResponseDto responseDto=new CourseResponseDto();
                                responseDto.setId(Course.getId());
                                responseDto.setCourseName(Course.getCourseName());
                                responseDto.setFees(Course.getFees());
                                return  responseDto;
                    }).toList();

            response.setCourseList(courseResponseDtosList);
        }

        return response;
    }

    public StudentResponse searchStudent(Long id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("please enter valid id"));
        return mapToResponse(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("please enter valid id"));
        studentRepository.delete(student);
    }

    public StudentResponse updateStudent(Long id,StudentRequestDto requestDto) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("please enter valid id"));

        if(requestDto.getFirstName()!=null){
            student.setFirstName(requestDto.getFirstName());
        }
        if(requestDto.getLastName()!=null){
            student.setLastName(requestDto.getLastName());
        }
        if(requestDto.getAge()!=null){
            student.setAge(requestDto.getAge());
        }
        if (requestDto.getEmail()!=null){
            student.setEmail(requestDto.getEmail());
        }
        if (requestDto.getPassWord()!=null){
            student.setPassWord(requestDto.getPassWord());
        }

        if (requestDto.getDepartment_id() != null) {
            Department department = departmentRepository.findById(requestDto.getDepartment_id())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + requestDto.getDepartment_id()));
            student.setDepartment(department);
        }

        studentRepository.save(student);

        return mapToResponse(student);
    }
}
