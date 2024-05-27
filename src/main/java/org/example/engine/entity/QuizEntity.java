package org.example.engine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column
    private String title;

    @Column
    private String text;

    @ElementCollection
    @CollectionTable(name = "quiz_options")
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @CollectionTable(name = "quiz_answers")
    private List<Integer> answer = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private String username;

}
