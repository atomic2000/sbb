package com.sc.exam.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public SiteUser create(String username, String email, String password) {
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setEmail(email);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //암호화 과정
    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);
    return user;
  }

}
