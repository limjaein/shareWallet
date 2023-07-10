package com.pjt.sharewallet.member.service;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.domain.Role;
import com.pjt.sharewallet.member.dto.MemberRequest;
import com.pjt.sharewallet.member.repository.MemberRepository;
import com.pjt.sharewallet.token.dto.JwtToken;
import com.pjt.sharewallet.token.provider.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder pwdEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;


    public Member join(MemberRequest memberRequest) {

        memberRepository.findByEmail(memberRequest.getEmail())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });

        return memberRepository.save(memberRequest.toEntity().hashPassword(pwdEncoder));
    }

    public JwtToken login(String email, String password) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication auth = authenticationManagerBuilder
                .getObject()
                .authenticate(authToken);

        return jwtTokenProvider.generateToken(auth);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = findEmail(email).get();

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(Role.USER.getDescription())
                .build();
    }

    public Optional<Member> findEmail(String email) {

        return Optional
                .ofNullable(memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        email + " 는 존재하지 않는 회원입니다.")));
    }
}
