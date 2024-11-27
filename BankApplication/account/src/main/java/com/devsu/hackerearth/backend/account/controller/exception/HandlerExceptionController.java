package com.devsu.hackerearth.backend.account.controller.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.devsu.hackerearth.backend.account.exceptions.AlreadyExistsException;
import com.devsu.hackerearth.backend.account.exceptions.AmountNotAvailable;
import com.devsu.hackerearth.backend.account.exceptions.NotFoundExcepcion;
import com.devsu.hackerearth.backend.account.model.dto.ErrorDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerExceptionController {
    @ExceptionHandler({ NotFoundExcepcion.class })
    public ResponseEntity<ErrorDto> notFoundException(Exception ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .error("Not found")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler({ AlreadyExistsException.class })
    public ResponseEntity<ErrorDto> alreadyExistsException(Exception ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(HttpStatus.CONFLICT.value())
                .error("Already exists")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

    @ExceptionHandler({ AmountNotAvailable.class })
    public ResponseEntity<ErrorDto> amountNotAvailableException(Exception ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .error("Amount not available")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

}
