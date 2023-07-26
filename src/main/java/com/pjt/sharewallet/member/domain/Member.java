package com.pjt.sharewallet.member.domain;

import com.pjt.sharewallet.transaction.domain.Transaction;
import com.pjt.sharewallet.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<MemberTrip> memberTrips = new ArrayList<>();

    public Member hashPassword(PasswordEncoder pwdEncoder) {
        this.password = pwdEncoder.encode(password);
        return this;
    }
}
