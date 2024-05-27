package org.example.engine.service;

import lombok.RequiredArgsConstructor;
import org.example.engine.dto.*;
import org.example.engine.entity.AppUserEntity;
import org.example.engine.entity.CompletedQuizEntity;
import org.example.engine.entity.QuizEntity;
import org.example.engine.mapper.QuizMapper;
import org.example.engine.repository.AppUserRepository;
import org.example.engine.repository.CompletedQuizRepository;
import org.example.engine.repository.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final CompletedQuizRepository completedQuizRepository;
    private final QuizMapper quizMapper;
    private final AppUserRepository appUserRepository;

    public ResponseEntity<ResponseQuizDto> createQuiz(RequestQuizDto quizRequest, UserDetails userDetails) {

        AppUserEntity userEntity = appUserRepository.findAppUserByUsername(userDetails.getUsername())
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found"));

        QuizEntity quizEntity = quizMapper.mapDtoToEntity(quizRequest, userEntity);
        quizRepository.save(quizEntity);
        return ResponseEntity.ok(quizMapper.mapEntityToDto(quizEntity));

    }

    public ResponseEntity<CompleteResponse> solveQuiz(int id, RequestSolveQuiz requestBody, UserDetails userDetails) {

        QuizEntity quizEntity = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found"));

        boolean success = checkAnswer(requestBody.getAnswer(), quizEntity.getAnswer());

        CompleteResponse response = CompleteResponse.builder()
                .success(success)
                .feedback(success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.")
                .build();

        if (success) {
            CompletedQuizEntity completedQuiz = CompletedQuizEntity.builder()
                    .id(id)
                    .username(userDetails.getUsername())
                    .completedAt(LocalDateTime.now())
                    .build();
            completedQuizRepository.save(completedQuiz);
        }

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Page<QuizEntity>> getAllQuizzesOnPage(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size
    ){
        Page<QuizEntity> pageQuiz = quizRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(pageQuiz, HttpStatus.OK);
    }

    public ResponseEntity<ResponseQuizDto> getQuizById(int id){

        QuizEntity quizEntity = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with such id doesn't exist"));

        return ResponseEntity.ok(quizMapper.mapEntityToDto(quizEntity));
    }

    public ResponseEntity<Page<AllCompletedQuizResponse>> getAllCompletedQuizzesOnPage(@RequestParam(defaultValue = "0") int page,
                                                                                       UserDetails userDetails) {
        String username = userDetails.getUsername();
        Pageable pageable = PageRequest.of(page, 10, Sort.by("completedAt").descending());

        Page<AllCompletedQuizResponse> completedQuizzesPage = completedQuizRepository.findAllByUsername(username, pageable)
                .map(quizMapper::mapEntityToDtoComplete);

        return ResponseEntity.ok(completedQuizzesPage);
    }


    public ResponseEntity<?> deleteQuiz(int id, UserDetails userDetails) {
        AppUserEntity userEntity = appUserRepository.findAppUserByUsername(userDetails.getUsername())
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found"));

        QuizEntity quizEntity = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found"));

        if (quizEntity.getUsername().equals(userEntity.getUsername())) {
            quizRepository.delete(quizEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can not delete this quiz!");
        }

        return ResponseEntity.noContent().build();
    }

    private boolean checkAnswer(List<Integer> userAnswer, List<Integer> correctAnswer) {

        return userAnswer.size() == correctAnswer.size()
                && new HashSet<>(correctAnswer).containsAll(userAnswer);
    }
}