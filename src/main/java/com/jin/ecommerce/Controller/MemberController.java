package com.jin.ecommerce.Controller;

import com.jin.ecommerce.dto.MemberDto;
import com.jin.ecommerce.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;


    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/user/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/user/login";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/user/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/user/admin")
    public String dispAdmin() {
        return "/user/admin";
    }
}