package guru.springframework.msscbeerservice.web.controller.advice;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        List<String> errors = new ArrayList<>(constraintViolations.size());
        constraintViolations.forEach(constraintViolation -> {
            String errorMsg = String.join(":", constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            errors.add(errorMsg);
        });
        return ResponseEntity.badRequest()
                .body(errors);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<String>> validationErrorHandler(BindException exception) {
        List<ObjectError> allErrors = exception.getAllErrors();
        List<String> errors = new ArrayList<>(allErrors.size());
        allErrors.forEach(objectError -> errors.add(objectError.getDefaultMessage()));
        return ResponseEntity.badRequest()
                .body(errors);
    }
}
