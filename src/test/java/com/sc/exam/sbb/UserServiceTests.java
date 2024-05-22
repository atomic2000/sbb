package com.sc.exam.sbb;

import com.sc.exam.sbb.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("회원가입이 가능하다.")
  public void t1() {
    userService.create("user1", "user@email.com", "1234");
  }
}