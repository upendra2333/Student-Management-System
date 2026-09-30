package com.Practice.StudentManagement.Controller;

import com.Practice.StudentManagement.Dtos.DepartmentRequestDto;
import com.Practice.StudentManagement.Dtos.DepartmentResponseDto;
import com.Practice.StudentManagement.Exceptions.ApiResponse;
import com.Practice.StudentManagement.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> createDepartment(@Valid @RequestBody DepartmentRequestDto departmentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Department added successfully",
                        departmentService.createDepartment(departmentRequestDto)
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> getDepartment(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Department fetched successfully",
                        departmentService.getDepartment(id)

                ));
    }

    @GetMapping("/allDept")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> getAllDepartments(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        true,
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Department fetched successfully",
                        departmentService.getAllDepartments()

                ));
    }
}
