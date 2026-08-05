package com.RestfulApi.TeacherInformationSystem.exception;

import com.RestfulApi.TeacherInformationSystem.response.CustomResponse;
import com.RestfulApi.TeacherInformationSystem.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<CustomResponse<?>> handleManagerNotFound(ManagerNotFoundException ex) {
        return build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<CustomResponse<?>> handleTeacherNotFound(TeacherNotFoundException ex) {
        return build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<CustomResponse<?>> handleStudentNotFound(StudentNotFoundException ex) {
        return build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SchoolClassNotFoundException.class)
    public ResponseEntity<CustomResponse<?>> handleSchoolClassNotFound(SchoolClassNotFoundException ex) {
        return build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {
        return build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<CustomResponse<?>> handleDuplicateEmail(DuplicateEmailException ex) {
        return build(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");
        return build(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponse<?>> handleRuntimeException(RuntimeException ex) {
        return build(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<?>> handleGenericException(Exception ex) {
        return build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<CustomResponse<?>> build(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponseUtil.error(message, status.value()));
    }
}
