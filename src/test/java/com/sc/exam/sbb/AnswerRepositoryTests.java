package com.sc.exam.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AnswerRepositoryTests {

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Test
  void testJpa() {
    Question q = questionRepository.findById(2).get();

    Answer a = new Answer();
    a.setContent(" 네 자동으로 생성됩니다.");
    a.setQuestion(q); // 어떤 질문의 답변인지 알기 위해서 Question 객체가 필요하다.
    a.setCreateDate(LocalDateTime.now());
    answerRepository.save(a);
  }
}
