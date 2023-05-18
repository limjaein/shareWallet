package com.pjt.sharewallet.member.repository;

import com.pjt.sharewallet.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {


    Optional<Member> findByusername(String username);
}
