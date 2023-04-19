package ms.spring.crudbasic.api.infrastructure.exceptions;

import jakarta.persistence.EntityNotFoundException;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseErrors;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseForbidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrorsException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400BadRequest(MethodArgumentNotValidException ex) {
        var errorList = ex.getFieldErrors().stream().map(FieldErrorsValidation::new).toList();
        return ResponseEntity.badRequest().body(ResponseErrors.argumentNotValid(errorList));
    }

    @ExceptionHandler(RuleViolationException.class)
    public ResponseEntity error400BadRequest(RuleViolationException ex) {
        return ResponseEntity.badRequest().body(ResponseErrors.ruleViolation(ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity error403Forbidden(ForbiddenAccessException ex) {
        return new ResponseEntity<ResponseForbidden>(ResponseForbidden.emitMessage(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity error403Forbidden(UsernameNotFoundException ex) {
        return new ResponseEntity<ResponseForbidden>(ResponseForbidden.emitMessage(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity error403Forbidden(InternalAuthenticationServiceException ex) {
        return new ResponseEntity<ResponseForbidden>(ResponseForbidden.emitMessage("Invalid credentials."), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404NotFound() {
        return ResponseEntity.notFound().build();
    }

    private record FieldErrorsValidation(String field, String message) {
        public FieldErrorsValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
