package org.example.engine.handler;


import org.example.engine.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class OwnExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> responseStatusExceptionHandler(
            ResponseStatusException exception,
            WebRequest request) {

        HttpStatus statusCode = HttpStatus.valueOf(exception.getStatusCode().value());
        String errorMessage = exception.getReason();

        ErrorDto apiError = ErrorDto.builder()
                .httpStatus(statusCode.value())
                .error(statusCode.getReasonPhrase())
                .message(errorMessage)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(apiError, statusCode);
    }
}
