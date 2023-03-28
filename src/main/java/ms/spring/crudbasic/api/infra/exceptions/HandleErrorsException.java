package ms.spring.crudbasic.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import ms.spring.crudbasic.api.infra.responses.ResponseErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrorsException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex) {
        var errorList = ex.getFieldErrors().stream().map(FieldErrorsValidation::new).toList();
        return ResponseEntity.badRequest().body(ResponseErrors.argumentNotValid(errorList));
    }

    @ExceptionHandler(RuleViolationException.class)
    public ResponseEntity error400(RuleViolationException ex) {
        return ResponseEntity.badRequest().body(ResponseErrors.ruleViolation(ex.getMessage()));
    }

    private record FieldErrorsValidation(String field, String message) {
        public FieldErrorsValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
