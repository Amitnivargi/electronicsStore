package com.lcwd.electronicsStrore.electronicsStore.Exceptions;

import com.lcwd.electronicsStrore.electronicsStore.DTO.ApiResponseMessege;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
     private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessege> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
         logger.info("Exception handler Invoked");
         ApiResponseMessege build = ApiResponseMessege.builder().messege(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
       return new ResponseEntity(build,HttpStatus.NOT_FOUND);
    }

    // method argumentNotvalidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotvalidException(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object> response=new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String messege=objectError.getDefaultMessage();
            String field=((FieldError) objectError).getField();
            response.put(field,messege);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    //handle APi bad API request
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponseMessege> handleBadRequest(BadApiRequest ex){
        logger.info("Bad APi request");
        ApiResponseMessege response = ApiResponseMessege.builder().messege(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);

    }
}
