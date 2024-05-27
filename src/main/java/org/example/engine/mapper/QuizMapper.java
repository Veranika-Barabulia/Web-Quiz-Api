package org.example.engine.mapper;

import org.example.engine.dto.AllCompletedQuizResponse;
import org.example.engine.dto.RequestQuizDto;
import org.example.engine.dto.ResponseQuizDto;
import org.example.engine.entity.AppUserEntity;
import org.example.engine.entity.CompletedQuizEntity;
import org.example.engine.entity.QuizEntity;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public ResponseQuizDto mapEntityToDto(QuizEntity quizEntity) {
        return ResponseQuizDto
                .builder()
                .id(quizEntity.getId())
                .title(quizEntity.getTitle())
                .text(quizEntity.getText())
                .options(quizEntity.getOptions())
                .build();
    }

    public QuizEntity mapDtoToEntity(RequestQuizDto quizDto, AppUserEntity userEntity) {
        return QuizEntity
                .builder()
                .title(quizDto.getTitle())
                .text(quizDto.getText())
                .options(quizDto.getOptions())
                .answer(quizDto.getAnswer())
                .username(userEntity.getUsername())
                .build();
    }

    public AllCompletedQuizResponse mapEntityToDtoComplete(CompletedQuizEntity completedQuizEntity) {
        return AllCompletedQuizResponse
                .builder()
                .id(completedQuizEntity.getId())
                .completedAt(completedQuizEntity.getCompletedAt())
                .build();
    }
}
