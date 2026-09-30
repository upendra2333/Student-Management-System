package com.Practice.StudentManagement.Exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String msg){
        super(msg);
    }
}
