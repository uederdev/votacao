package br.com.ueder.votacao_api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiErrorUtil {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        MensagemDTO mensagemDTO = new MensagemDTO(e.getMessage(), LocalDateTime.now(), path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemDTO);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        MensagemDTO mensagemDTO = new MensagemDTO(e.getMessage(), LocalDateTime.now(), path);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemDTO);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        MensagemDTO mensagemDTO = new MensagemDTO(e.getMessage(), LocalDateTime.now(), path);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemDTO);
    }


}
