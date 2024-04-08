package com.sc.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller : 스프링부트한테 해당 클래스는 컨트롤러 역할이라고 알려준다.
@Controller
public class HomeController {
  @RequestMapping("/sbb")
  @ResponseBody
  public String showHome() {
    return "첫 시작";
  }
}
