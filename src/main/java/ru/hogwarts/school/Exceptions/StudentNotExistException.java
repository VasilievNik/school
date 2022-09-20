package ru.hogwarts.school.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class StudentNotExistException extends RuntimeException {
    public StudentNotExistException() {
    }
}