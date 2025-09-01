// package com.examly.springapp.exception;

// import com.examly.springapp.dto.ErrorResponse;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;

// import java.time.LocalDateTime;

// @ControllerAdvice
// public class GlobalExceptionHandler {

//     // Handle any runtime exception
//     @ExceptionHandler(RuntimeException.class)
//     public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
//         ErrorResponse errorResponse = new ErrorResponse(
//                 HttpStatus.BAD_REQUEST.value(),
//                 ex.getMessage(),
//                 LocalDateTime.now()
//         );
//         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//     }

//     // Example: Handle resource not found
//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
//         ErrorResponse errorResponse = new ErrorResponse(
//                 HttpStatus.NOT_FOUND.value(),
//                 ex.getMessage(),
//                 LocalDateTime.now()
//         );
//         return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//     }
// }


package com.examly.springapp.exception;

import com.examly.springapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                List.of(ex.getMessage()), // wrap message into a list
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle resource not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                List.of(ex.getMessage()), // wrap message into a list
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
