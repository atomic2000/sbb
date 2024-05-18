package com.sc.exam.sbb.answer;

import com.sc.exam.sbb.question.Question;
import com.sc.exam.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

  private final QuestionService questionService;

  @PostMapping("/create/{id}")
  public String createAnswer(Model model, @PathVariable int id, String content) {
    Question question = questionService.getQuestion(id);

    // 답변 등록 시작
    // 답변 등록 끝

    return "redirect:/question/detail/%d".formatted(id);
  }
}
