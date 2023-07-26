package com.pjt.sharewallet.member.domain;

import com.pjt.sharewallet.trip.domain.Trip;
import javax.persistence.*;

@Entity
public class MemberTrip {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    private Trip trip;
}
