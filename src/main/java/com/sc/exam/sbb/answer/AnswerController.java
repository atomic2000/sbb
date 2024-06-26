package com.sc.exam.sbb.answer;

import com.sc.exam.sbb.question.Question;
import com.sc.exam.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

  private final QuestionService questionService;
  private final AnswerService answerService;

  @PostMapping("/create/{id}")
  public String createAnswer(Model model, @PathVariable Long id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
    Question question = questionService.getQuestion(id);

    if(bindingResult.hasErrors()) {
      model.addAttribute("question", question);
      return "question_detail";
    }

    // 답변 등록 시작
    answerService.create(question, answerForm.getContent());
    // 답변 등록 끝

    return "redirect:/question/detail/%d".formatted(id);
  }
}
