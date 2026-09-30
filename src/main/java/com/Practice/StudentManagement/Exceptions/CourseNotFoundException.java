package com.Practice.StudentManagement.Exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String msg){
        super(msg);
    }
}
