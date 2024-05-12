package com.sc.exam.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

  @Autowired
  private QuestionRepository questionRepository;

  @Test
  void contextLoads() {

  }

  @Test
  void testJpa() {
    Question q1 = new Question();
    q1.setSubject("sbb가 무엇인가요?");
    q1.setContent("sbb에 대해서 알고 싶습니다.");
    q1.setCreateDate(LocalDateTime.now());
    this.questionRepository.save(q1); // 첫번째 질문 저장

    Question q2 = new Question();
    q2.setSubject("스프링부트 모델 질문입니다.");
    q2.setContent("id는 자동으로 생성되나요?");
    q2.setCreateDate(LocalDateTime.now());
    this.questionRepository.save(q2); // 두번째 질문 저장

    // id는 최소 0번보다 크다.
    assertThat(q1.getId()).isGreaterThan(0);
    // 두 번째 id는 첫 번째 id보다 크다.
    assertThat(q2.getId()).isGreaterThan(q1.getId());
  }

  @Test
  void testJpa2() {
    List<Question> all = questionRepository.findAll();
    assertEquals(4, all.size()); // 질문의 총개수가 4개가 맞는지.

    Question q = all.get(0);
    assertEquals("sbb가 무엇인가요?", q.getSubject());
  }

  @Test
  void testJpa3() {
    Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
    assertEquals(2, q.getId()); //해당 서브젝트 내용 id가 2번이 맞는지.
  }

  @Test
  void testJpa4() {
    Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
    assertEquals(1, q.getId());
  }
}
