package com.sc.exam.sbb;

import com.sc.exam.sbb.answer.Answer;
import com.sc.exam.sbb.answer.AnswerRepository;
import com.sc.exam.sbb.question.Question;
import com.sc.exam.sbb.question.QuestionRepository;
import com.sc.exam.sbb.user.SiteUser;
import com.sc.exam.sbb.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AnswerRepositoryTests {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  private void clearData() {
    clearData(userRepository, answerRepository, questionRepository);
  }
  public static void clearData(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
    UserServiceTests.clearData(userRepository, answerRepository, questionRepository);
  }

  private void createSampleData() {

    QuestionRepositoryTests.createSampleData(questionRepository);

    Question q = questionRepository.findById(1L).get();

    Answer a1 = new Answer();
    a1.setContent("sbb는 질문답변 게시판입니다.");
    a1.setCreateDate(LocalDateTime.now());
    a1.setAuthor(new SiteUser(2L));
    q.addAnswer(a1);
    answerRepository.save(a1);

    Answer a2 = new Answer();
    a2.setContent("sbb에서는 주로 스프링관련 내용을 다룹니다.");
    a2.setCreateDate(LocalDateTime.now());
    a2.setAuthor(new SiteUser(2L));
    q.addAnswer(a2);
    answerRepository.save(a2);

    questionRepository.save(q);
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void 저장() {
    Question q = questionRepository.findById(2L).get();

    Answer a1 = new Answer();
    a1.setContent("네 자동으로 생성됩니다.");
    a1.setCreateDate(LocalDateTime.now());
    q.addAnswer(a1);

    Answer a2 = new Answer();
    a2.setContent("네네~ 맞아요!");
    a2.setCreateDate(LocalDateTime.now());
    q.addAnswer(a2);

    questionRepository.save(q);
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void 조회() {
    Answer a = answerRepository.findById(1L).get();
    assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판입니다.");
  }

  @Test
  @Transactional
  @Rollback(value = false)
  // 답변을 통해서 질문을 조회
  void 관련된_question_조회() {
    Answer a = answerRepository.findById(1L).get();
    Question q = a.getQuestion();

    assertThat(q.getId()).isEqualTo(1);
  }

  @Test
  @Transactional // 한 묶음으로 연결해주는 어노테이션(데이터베이스 연결을 안끊는다.)
  @Rollback(false)
  void question으로부터_관련된_답변들_조회() {
    // SELECT * FROM question WHERE id = 1;
    // 관련 답변이 하나도 없는 상태에서 쿼리 발생
    Question q = questionRepository.findById(1L).get();
    // 여기서 DB 연결이 끊겨서 오류 발생됐었는데 50번줄에 q.getAnswerList().add(a1); 로 정상 작동됨.

    // SELECT * FROM answer WHERE question_id = 1;
    List<Answer> answerList = q.getAnswerList();

    assertThat(answerList.size()).isEqualTo(2);
    assertThat(answerList.get(0).getContent()).isEqualTo("sbb는 질문답변 게시판입니다.");
  }
}



































