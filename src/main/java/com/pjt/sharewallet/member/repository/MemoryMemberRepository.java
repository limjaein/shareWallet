package com.pjt.sharewallet.member.repository;

import com.pjt.sharewallet.member.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Integer, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(int id) {
        return store.get(id);
    }
}
