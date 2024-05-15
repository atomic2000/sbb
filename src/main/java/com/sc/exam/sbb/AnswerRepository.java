package com.sc.exam.sbb;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

  @Transactional
  @Modifying
  @Query(value = "ALTER SEQUENCE sbb.answer_id_seq RESTART WITH 1", nativeQuery = true)
  void resetSequence();

  @Transactional
  @Modifying
  @Query(value = "TRUNCATE TABLE sbb.answer cascade", nativeQuery = true)
  void truncate();

}
