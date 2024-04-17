package com.sc.exam.sbb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.util.Introspection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// @Controller : 스프링부트한테 해당 클래스는 컨트롤러 역할이라고 알려준다.
@Controller
public class HomeController {

  private int increaseNo;

  public HomeController() {
    increaseNo = -1;
  }

  @RequestMapping("/sbb")
  // @ResponseBody
  // 아래 함수의 리턴값을 그대로 브라우저에 표시
  // 아래 함수의 리턴값을 문자열화 해서 브라우저 응답을 바디에 담는다.
  @ResponseBody
  public String showHome() {
    return "안녕하세요!";
  }

  @RequestMapping("/test")
  @ResponseBody
  public String showTest() {
    return """
        <h1>안녕하세요.</h1>
        <input type='text' placeholder="입력해주세요."/>
        """;
  }

  @GetMapping("/page1")
  @ResponseBody
  public String showGet() {
    return """
        <form method='POST' action='/page2'>
        <input type="number" name="age" placeholder="나이 입력" />
        <input type="submit" value="page2로 POST 방식으로 이동" />
        </form>
        """;
  }

  @GetMapping("/page2")
  @ResponseBody
  public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
    return """
        <h1>입력된 나이 : %d</h1>
        <h1>안녕하세요. GET 방식으로 오신걸 환영합니다.</h1>        
        """.formatted(age);
  }

  @PostMapping("/page2")
  @ResponseBody
  public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
    return """
        <h1>입력된 나이 : %d</h1>
        <h1>안녕하세요. POST 방식으로 오신걸 환영합니다.</h1>
        """.formatted(age);
  }

  @GetMapping("/plus")
  @ResponseBody
  public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
    return a + b;
  }

  @GetMapping("/plus2")
  @ResponseBody
  public void showPlus2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int a = Integer.parseInt(req.getParameter("a"));
    int b = Integer.parseInt(req.getParameter("b"));

    resp.getWriter().append(a + b + "");
  }

  @GetMapping("/minus")
  @ResponseBody
  public int showMinus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
    return a - b;
  }

  @GetMapping("/increase")
  @ResponseBody
  public int showIncrease() {
    increaseNo++;
    return increaseNo;
  }

  @GetMapping("/gugudan")
  @ResponseBody
  public String showGugusan(Integer dan, Integer limit) {
    if (dan == null) {
      dan = 9;
    }

    if (limit == null) {
      limit = 9;
    }

    String gugudanFormat = "";

    for(int i = 0; i < limit; i++) {
      gugudanFormat += "%d * %d = %d\n".formatted(dan, i, dan * i);
    }

    return gugudanFormat;
  }

  @GetMapping("/gugudan2")
  @ResponseBody
  public String showGugudan2(Integer dan, Integer limit) {
    if (dan == null) {
      dan = 9;
    }

    if (limit == null) {
      limit = 9;
    }

    // final 수식어가 붙으면 해당 변수는 상수처리된다.
    final Integer finalDan = dan;
    return IntStream.rangeClosed(1, limit)
        .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
        .collect(Collectors.joining("<br>"));
  }

  @GetMapping("/mbti/{name}")
  @ResponseBody
  public String showMbti(@PathVariable String name) {
    return switch (name) {
      case "홍길동" -> {
        char j = 'J';
        yield "INF" + j;
      }
      case "홍길순" -> "ENFP";
      case "임꺽정", "신짱구" -> "ESFJ";

      default -> "모름";
    };
  }

  // 문제 => https://www.youtube.com/watch?v=XHJyphpwZbA
  // 해답 => https://www.youtube.com/watch?v=9e5_Et2ESdQ
  @GetMapping("/saveSession/{name}/{value}")
  @ResponseBody
  public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
    HttpSession session = req.getSession();

    // req => 쿠키 => JSESSIONID => 세션을 얻을 수 있다.
    session.setAttribute(name, value);

    return "세선 변수의 %s의 값이 %s(으)로 설정되었습니다.".formatted(name,value);
  }

  @GetMapping("/getSession/{name}")
  @ResponseBody
  public String getSession(@PathVariable String name, HttpServletRequest session) {
    String value = (String) session.getAttribute(name);

    return "세션변수의 %s의 값이 %s입니다.".formatted(name,value);
  }
  // 여기까지 위 문제 관련 답변


}
