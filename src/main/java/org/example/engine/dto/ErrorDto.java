package org.example.engine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDto {

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    private int httpStatus;

    private String error;

    private String message;

    private String path;

}
