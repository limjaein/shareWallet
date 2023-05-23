package com.pjt.sharewallet.member.service;

import com.pjt.sharewallet.config.SecurityConfig;
import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.domain.Role;
import com.pjt.sharewallet.member.dto.MemberRequest;
import com.pjt.sharewallet.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder pwdEncoder;


    public Member join(MemberRequest memberRequest) {

        memberRepository.findByMemberId(memberRequest.getMemberId())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });

        return memberRepository.save(memberRequest.toEntity().hashPassword(pwdEncoder));
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        Member member = findMemberId(memberId).get();

        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .authorities(Role.USER.getDescription())
                .build();
    }

    public Optional<Member> findMemberId(String memberId) {

        return Optional
                .ofNullable(memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        memberId + " 는 존재하지 않는 회원입니다.")));
    }
}
