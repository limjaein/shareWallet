package com.pjt.sharewallet.member.repository;

import com.pjt.sharewallet.member.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(int id);
}
