package com.sc.exam.sbb.user;

import com.sc.exam.sbb.SignupEmailDuplicatedException;
import com.sc.exam.sbb.SignupUsernameDuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //생성자 주입
@Service
public class UserService {

  private final UserRepository userRepository;
  // 스프링이 책임지고 PasswordEncoder 타입의 객체를 만들어야 하는 상황
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String username, String email, String password)
  throws SignupUsernameDuplicatedException, SignupEmailDuplicatedException {
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));

    try {
      userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      if(userRepository.existsByUsername(username)) {
        throw new SignupUsernameDuplicatedException("이미 사용중인 username입니다.");
      } else {
        throw new SignupEmailDuplicatedException("이미 사용중인 email입니다.");
      }
    }
    return user;
  }
}
