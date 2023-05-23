package com.pjt.sharewallet.member.dto;

import com.pjt.sharewallet.member.domain.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotBlank
    private String memberId;

    @NotBlank
    private String password;

    public Member toEntity() {
        return Member.builder()
                .memberId(this.memberId)
                .password(this.password)
                .build();
    }
}
