package org.example.engine.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.engine.dto.*;
import org.example.engine.entity.QuizEntity;
import org.example.engine.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quizzes")
    public ResponseEntity<ResponseQuizDto> createQuiz(@Valid @RequestBody RequestQuizDto quizRequest,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.createQuiz(quizRequest, userDetails);
    }

    @PostMapping( "/quizzes/{id}/solve")
    public ResponseEntity<CompleteResponse> solveQuiz(@PathVariable int id,
                                                      @RequestBody RequestSolveQuiz requestBody,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.solveQuiz(id, requestBody, userDetails);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<Page<QuizEntity>> getAllQuizzesOnPage(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size
    ){
        return quizService.getAllQuizzesOnPage(page, size);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<ResponseQuizDto> getQuizById(@PathVariable int id){
        return quizService.getQuizById(id);
    }

    @GetMapping("/quizzes/completed")
    public ResponseEntity<Page<AllCompletedQuizResponse>> getAllCompletedQuizzesOnPage(@RequestParam(defaultValue = "0") int page,
                                                                                       @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.getAllCompletedQuizzesOnPage(page, userDetails);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.deleteQuiz(id, userDetails);
    }
}