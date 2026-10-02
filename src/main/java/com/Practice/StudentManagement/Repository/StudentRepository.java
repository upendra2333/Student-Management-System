package com.Practice.StudentManagement.Repository;

import com.Practice.StudentManagement.Dtos.StudentResponse;
import com.Practice.StudentManagement.Model.Student;
import com.Practice.StudentManagement.Projections.StudentProjection;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    List<StudentProjection> findBy();

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Student findByEmail(@RequestParam("email") String email);


    @Query("""
        SELECT s
        FROM STUDENT s
        JOIN s.courseList c
        WHERE LOWER(c.courseName)=LOWER(:courseName)
     """)
    List<Student>findStudentByCourse(@Param("courseName") String courseName);
}
