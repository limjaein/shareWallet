package com.pjt.sharewallet.member.service;

import com.pjt.sharewallet.member.domain.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(int id);
}
