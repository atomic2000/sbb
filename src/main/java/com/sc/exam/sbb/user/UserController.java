package com.sc.exam.sbb.user;

import com.sc.exam.sbb.SignupEmailDuplicatedException;
import com.sc.exam.sbb.SignupUsernameDuplicatedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @GetMapping("/signup")
  public String signup(UserCreateForm userCreateForm) {
    return "signup_form";
  }

  @PostMapping("/singup")
  public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "signup_form";
    }

    if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
      bindingResult.rejectValue("signupFailed", "passwordInCorrect","2개의 패스워드가 일치하지 않습니다.");
      return "signup_form";
    }

    try {
      userService.create(userCreateForm.getUsername(),userCreateForm.getEmail(),userCreateForm.getPassword1());
    } catch (SignupUsernameDuplicatedException e) {
      bindingResult.reject("signupUsernameFailed", e.getMessage());
      return "signup_form";
    } catch (SignupEmailDuplicatedException e) {
      bindingResult.reject("signupEmileFailed", e.getMessage());
      return "signup_form";
    }

    return "redirect:/";
  }
}
