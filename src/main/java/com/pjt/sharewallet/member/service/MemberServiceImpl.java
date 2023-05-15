package com.pjt.sharewallet.member.service;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(int id) {
        return memberRepository.findById(id);
    }
}
