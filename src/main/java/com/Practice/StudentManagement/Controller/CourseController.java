package com.Practice.StudentManagement.Controller;

import com.Practice.StudentManagement.Dtos.CourseRequestDto;
import com.Practice.StudentManagement.Dtos.CourseResponseDto;
import com.Practice.StudentManagement.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<CourseResponseDto> addCourse(@Valid @RequestBody CourseRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createNewCourse(requestDto));
    }

    @GetMapping("/getCourse")
    public ResponseEntity<CourseResponseDto> getCourse(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.getCourse(id));
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<CourseResponseDto>> getAllCourse(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.getAllCourse());
    }

    @DeleteMapping("/delete")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "Successfully  deleted course  with this id";
    }


}
