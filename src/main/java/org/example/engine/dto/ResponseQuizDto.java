package org.example.engine.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseQuizDto {

    private int id;

    private String title;

    private String text;

    private List<String> options;

}
