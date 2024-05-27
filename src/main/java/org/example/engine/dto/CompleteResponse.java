package org.example.engine.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteResponse {

    private boolean success;
    private String feedback;
}
