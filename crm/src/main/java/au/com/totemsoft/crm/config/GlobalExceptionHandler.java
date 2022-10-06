package au.com.totemsoft.crm.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import au.com.totemsoft.crm.exception.NotAllowedException;
import au.com.totemsoft.crm.exception.NotFoundException;
import au.com.totemsoft.crm.model.ErrorResponse;
import au.com.totemsoft.crm.model.ValidationError;
import au.com.totemsoft.crm.model.ValidationErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> defaultErrorHandler(Exception ex, WebRequest request) throws Exception {
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return error(status, ex, "Unexpected Server Error");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException ex, WebRequest request) throws Exception {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return error(status, ex, null);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<ErrorResponse> handle(NotAllowedException ex, WebRequest request) throws Exception {
        final HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        return error(status, ex, null);
    }

    private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception ex, String message) {
        final ErrorResponse error = new ErrorResponse(
                status.value(),
                message != null ? message : ex.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            ValidationError errorsItem = new ValidationError(
                error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName(),
                error.getDefaultMessage());
            errors.add(errorsItem);
        });
        return new ResponseEntity<>(new ValidationErrorResponse(errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
