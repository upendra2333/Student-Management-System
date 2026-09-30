package com.Practice.StudentManagement.Repository;

import com.Practice.StudentManagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
