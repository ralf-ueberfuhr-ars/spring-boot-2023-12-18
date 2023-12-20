package de.sample.schulung.spring.blog.boundary;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings("NullableProblems")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  void handleValidationException() {
    // do nothing
    // return ResponseEntity, Fehler-Object (JSON), ProblemDetail
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
    HttpMediaTypeNotSupportedException ex,
    HttpHeaders headers,
    HttpStatusCode status,
    WebRequest request
  ) {
    // eigenes Fehlerhandling
    return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatusCode status,
    WebRequest request
  ) {
    // eigenes Fehlerhandling
    return super.handleMethodArgumentNotValid(ex, headers, status, request);
  }
}
