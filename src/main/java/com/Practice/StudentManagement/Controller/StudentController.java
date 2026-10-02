package com.Practice.StudentManagement.Controller;

import com.Practice.StudentManagement.Dtos.StudentRequestDto;
import com.Practice.StudentManagement.Dtos.StudentResponse;
import com.Practice.StudentManagement.Projections.StudentProjection;
import com.Practice.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("createStudent")
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createStudent(requestDto));
    }

    @GetMapping("getStudent/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentService.searchStudent(id));
    }

    @DeleteMapping("deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id){
         studentService.deleteStudent(id);
    }

    @PatchMapping("updateStudent/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,@RequestBody StudentRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentService.updateStudent(id,requestDto));
    }

    @GetMapping("getStudents")
    public ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentService.getAllStudents(pageable));
    }

    @GetMapping("projections")
    public List<StudentProjection> getDetails(){
        return studentService.getNecessaryDetails();
    }

    @GetMapping("byEmail")
    public StudentResponse getStudentByEmail(@RequestParam("email") String email){
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("getStudentByCourse")
    public List<StudentResponse> getStudentsByCourse(@RequestParam("courseName")
                                                         String courseName){
        return studentService.getStudentByCourse(courseName);
    }

}
