package com.pjt.sharewallet.member.controller;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.dto.MemberRequest;
import com.pjt.sharewallet.member.repository.MemberRepository;
import com.pjt.sharewallet.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    private final AuthenticationManager authenticationManager;

    @RequestMapping(value="/{memberId}", method=RequestMethod.GET)
    public ResponseEntity<String> findMember(@PathVariable("memberId") String memberId) {

        Member member = null;
        try {
            member = memberService.findMemberId(memberId).get();
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memberId);
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> joinMember(@Valid @RequestBody MemberRequest memberRequest) {

        try {
            memberService.join(memberRequest);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginMember(@Valid @RequestBody MemberRequest memberRequest) {

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

        return ResponseEntity.ok().build();
    }
}
