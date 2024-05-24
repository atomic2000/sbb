package com.sc.exam.sbb;

import com.sc.exam.sbb.answer.AnswerRepository;
import com.sc.exam.sbb.question.QuestionRepository;
import com.sc.exam.sbb.question.QuestionService;
import com.sc.exam.sbb.user.UserRepository;
import com.sc.exam.sbb.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

  @Autowired
  private UserService userService;

  @Autowired
  private AnswerRepository answerRepository;
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  private void createSampleData() {
    createSampleData(userService);
  }

  private void createSampleData(UserService userService) {
    userService.create("admin", "admin@test.com", "1234");
    userService.create("user1", "user1@test.com", "1234");
  }

  private void clearData() {
    clearData(userRepository, answerRepository, questionRepository);
  }

  private void clearData(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
    AnswerRepositoryTests.clearData(answerRepository, questionRepository);
    userRepository.deleteAll();
    userRepository.truncate();
  }

  @Test
  @DisplayName("회원가입이 가능하다.")
  public void t1() {
    userService.create("user2", "user2@email.com", "1234");
  }
}
