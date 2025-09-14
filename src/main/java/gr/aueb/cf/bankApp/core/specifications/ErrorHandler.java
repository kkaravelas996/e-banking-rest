package gr.aueb.cf.bankApp.core.specifications;

import gr.aueb.cf.bankApp.core.exceptions.*;
import gr.aueb.cf.bankApp.dto.ResponseMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleValidationException(ValidationException ex){
        BindingResult bindingResult = ex.getBindingResult();
        Map<String,String> errors = new HashMap<>();

        for (FieldError fieldError: bindingResult.getFieldErrors()){
            errors.put(fieldError.getCode(), fieldError.getDefaultMessage());

        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AppObjectNotAuthorizedException.class})
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppObjectNotAuthorizedException e){
        return new ResponseEntity<>(new ResponseMessageDTO(e.getCode(), e.getMessage()),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InvalidArgumentException.class})
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(InvalidArgumentException e){
        return new ResponseEntity<>( new ResponseMessageDTO(e.getCode(),e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ObjectAlreadyExistsException.class})
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(ObjectAlreadyExistsException e){
        return new ResponseEntity<>( new ResponseMessageDTO(e.getCode(),e.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(ObjectNotFoundException e){
        return new ResponseEntity<>( new ResponseMessageDTO(e.getCode(),e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AppServerException.class})
    public ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(AppServerException e) {
        return new ResponseEntity<>(new ResponseMessageDTO(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
