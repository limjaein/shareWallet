package com.pjt.sharewallet.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    public Member hashPassword(PasswordEncoder pwdEncoder) {
        this.password = pwdEncoder.encode(password);
        return this;
    }
}
