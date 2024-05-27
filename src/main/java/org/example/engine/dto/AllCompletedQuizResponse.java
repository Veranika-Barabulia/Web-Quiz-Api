package org.example.engine.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllCompletedQuizResponse {
    private int id;
    private LocalDateTime completedAt;

}