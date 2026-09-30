package com.Practice.StudentManagement.Controller;

import com.Practice.StudentManagement.Dtos.StudentRequestDto;
import com.Practice.StudentManagement.Dtos.StudentResponse;
import com.Practice.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
