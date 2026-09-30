package com.Practice.StudentManagement.Exceptions;


public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String msg){
        super(msg);
    }
}
