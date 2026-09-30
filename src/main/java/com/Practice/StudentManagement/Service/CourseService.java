package com.Practice.StudentManagement.Service;

import com.Practice.StudentManagement.Dtos.CourseRequestDto;
import com.Practice.StudentManagement.Dtos.CourseResponseDto;
import com.Practice.StudentManagement.Exceptions.CourseNotFoundException;
import com.Practice.StudentManagement.Model.Course;
import com.Practice.StudentManagement.Repository.CourseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseResponseDto createNewCourse(@Valid CourseRequestDto requestDto) {
        Course course=new Course();
        course.setCourseName(requestDto.getCourseName());
        course.setFees(requestDto.getFees());

        courseRepository.save(course);
        return mapToCourseResponse(course);
    }

    public CourseResponseDto mapToCourseResponse(Course course) {
        CourseResponseDto responseDto=new CourseResponseDto();
        responseDto.setId(course.getId());
        responseDto.setCourseName(course.getCourseName());
        responseDto.setFees(course.getFees());
        return responseDto;
    }


    public CourseResponseDto getCourse(Long id) {

        Course course=courseRepository.findById(id)
                .orElseThrow(()->new CourseNotFoundException("course with this id is not found"));
        return mapToCourseResponse(course);
    }

    public List<CourseResponseDto> getAllCourse() {
        return courseRepository.findAll()
                .stream().map(this::mapToCourseResponse)
                .toList();

    }

    public void deleteCourse(Long id) {
        Course course=courseRepository.findById(id)
                .orElseThrow(()->new CourseNotFoundException("course with this id is not found"));
        courseRepository.deleteById(id);
    }
}
