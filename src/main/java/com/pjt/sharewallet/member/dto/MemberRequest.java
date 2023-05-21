package com.pjt.sharewallet.member.dto;

import com.pjt.sharewallet.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private int id;
    private String memberId;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .memberId(this.memberId)
                .password(this.password)
                .build();
    }
}
