package com.pjt.sharewallet.member.dto;

import com.pjt.sharewallet.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}
