package org.example.engine.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAppUserDto {

    @Pattern(regexp = "\\w+[.\\w+]*@\\w+\\.\\w+", message = "Email should be valid")
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;
}
