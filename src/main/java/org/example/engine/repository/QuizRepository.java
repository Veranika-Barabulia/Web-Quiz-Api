package org.example.engine.repository;

import org.example.engine.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizRepository extends JpaRepository<QuizEntity, Integer>, PagingAndSortingRepository<QuizEntity, Integer> {

}