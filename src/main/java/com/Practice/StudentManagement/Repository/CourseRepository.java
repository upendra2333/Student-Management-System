package com.Practice.StudentManagement.Repository;

import com.Practice.StudentManagement.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
