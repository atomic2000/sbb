package com.sc.exam.sbb.question;

import com.sc.exam.sbb.RepositoryUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer>, RepositoryUtil {

  @Transactional
  @Modifying
  @Query(value = "ALTER SEQUENCE sbb.question_id_seq RESTART WITH 1", nativeQuery = true)
  void resetSequence();

  @Transactional
  @Modifying
  @Query(value = "TRUNCATE TABLE sbb.question cascade", nativeQuery = true)
  void truncate();

  Question findBySubject(String subject);

  Question findBySubjectAndContent(String subject, String content);

  List<Question> findBySubjectLike(String subject);
}
