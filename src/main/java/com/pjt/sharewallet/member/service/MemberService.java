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

        if (isMemberExist(memberRequest.getMemberId())) {
            // 아이디 중복시 예외 처리
        }
        return memberRepository.save(memberRequest.toEntity().hashPassword(pwdEncoder));
    }

    private boolean isMemberExist(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);

        return member.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        if(!isMemberExist(memberId)) {
            throw new UsernameNotFoundException(memberId);
        }

        Member member = memberRepository.findByMemberId(memberId).get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(memberId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getDescription()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getDescription()));
        }

        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }
}
