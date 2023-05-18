package com.pjt.sharewallet.member.service;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.domain.Role;
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

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private final MemberRepository memberRepository;
    private final PasswordEncoder pwdEncoder;


    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> _member = memberRepository.findByusername(username);

        if(_member.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        Member member = _member.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getDescription()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getDescription()));
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }
}
