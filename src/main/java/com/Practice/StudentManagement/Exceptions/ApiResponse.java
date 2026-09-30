package com.Practice.StudentManagement.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Boolean status;
    private LocalDateTime timeStamp;
    private Integer statusCode;
    private String Message;
    private T data;

}
