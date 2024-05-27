package org.example.engine.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestSolveQuiz {

    private List<Integer> answer;
}
