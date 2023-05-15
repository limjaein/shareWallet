package com.pjt.sharewallet;

import com.pjt.sharewallet.member.repository.MemoryMemberRepository;
import com.pjt.sharewallet.member.service.MemberService;
import com.pjt.sharewallet.member.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
}
