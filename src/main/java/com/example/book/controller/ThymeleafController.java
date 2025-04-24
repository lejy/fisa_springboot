package com.example.book.controller;

// controller.ThymeleafController.java

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sample")
public class ThymeleafController {

    @GetMapping
    public String thymeleafSample(HttpServletRequest request,
                                  HttpServletResponse response,
                                  HttpSession session,
                                  Model model) {

        // 세션 설정
        session.setAttribute("sessionMessage", "세션에 저장된 메시지입니다.");

        // 쿠키 설정
        Cookie cookie = new Cookie("myCookie", "쿠키값입니다");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1시간
        response.addCookie(cookie);

        // 모델에 사용자 정보 및 리스트 추가
        model.addAttribute("user", new User("이순신", 25));
        model.addAttribute("items", List.of("사과", "바나나", "체리"));

        // 세션에서 메시지 가져오기
        String sessionMessage = (String) session.getAttribute("sessionMessage");
        model.addAttribute("sessionMessage", sessionMessage); // 모델 서버에 있는 세션을 달아서 사용자에게 전달!

        // 쿠키 읽기
        String cookieValue = null;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("myCookie".equals(c.getName())) {
                    cookieValue = c.getValue();
                    break;
                }
            }
        }
        model.addAttribute("cookieMessage", cookieValue); // 스프링부트에서 mvc 패턴으로 데이터를 전달!

        return "sample";
    }
}

@AllArgsConstructor
@Data
class User {
    String name;
    int age;
}
