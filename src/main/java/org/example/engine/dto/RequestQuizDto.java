package org.example.engine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestQuizDto {

    @NotBlank(message = "title of the quiz is required")
    @Pattern(regexp = ".*\\D.*", message = "Title must contain at least one non-digit character")
    private String title;

    @NotBlank(message = "text of the quiz is required")
    @Pattern(regexp = ".*\\D.*", message = "Text must contain at least one non-digit character")
    private String text;

    @NotNull(message = "options is required")
    @Size(min = 2)
    private List<String> options;

    private List<Integer> answer;
}
