package com.pjt.sharewallet.member.controller;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.dto.MemberRequest;
import com.pjt.sharewallet.member.repository.MemberRepository;
import com.pjt.sharewallet.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> joinMember(@RequestBody MemberRequest memberRequest) {

        memberService.join(memberRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginMember(@RequestBody MemberRequest memberRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(memberRequest.getMemberId(), memberRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        System.out.println("성공");
        return ResponseEntity.ok().build();
    }
}
