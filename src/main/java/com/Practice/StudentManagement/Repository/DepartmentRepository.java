package com.Practice.StudentManagement.Repository;

import com.Practice.StudentManagement.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
