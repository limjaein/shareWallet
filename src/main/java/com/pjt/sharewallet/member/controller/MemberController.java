package com.pjt.sharewallet.member.controller;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public String joinMember(@RequestBody Member member) {

        memberService.join(member);
        return "add member";
    }

    @PostMapping("/login")
    public String loginMember(@RequestBody Member member) {

        //memberService.login(member);
        return "login member";
    }
}
