package co.com.doublev.api.handlingexception;

import co.com.doublev.usecase.exceptions.ExceptionResponse;
import co.com.doublev.usecase.exceptions.custom.CustomException;
import co.com.doublev.usecase.exceptions.custom.ResourceAlreadyExistsException;
import co.com.doublev.usecase.exceptions.custom.ResourceNotFoundException;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setDate(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ResourceAlreadyExistsException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setCode("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setDate(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customException(CustomException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handle(ValidationException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("UNPROCESSABLE_ENTITY");
        response.setMessage(ex.getMessage());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ExceptionResponse> invalidAccess(InvalidDataAccessApiUsageException ex){
        ExceptionResponse response=new ExceptionResponse();
        response.setCode("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
