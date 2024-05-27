package org.example.engine.repository;

import org.example.engine.entity.CompletedQuizEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedQuizRepository extends JpaRepository<CompletedQuizEntity, Long> {

    Page<CompletedQuizEntity> findAllByUsername(String username, Pageable pageable);
}
