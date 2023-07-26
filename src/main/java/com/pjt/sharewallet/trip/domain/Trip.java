package com.pjt.sharewallet.trip.domain;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.member.domain.MemberTrip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "TRIP_ID")
    private int id;

    private String title;

    private String start_date;

    private String end_date;

    private boolean thumb_flag;

    @OneToMany(mappedBy = "trip")
    private List<MemberTrip> memberTrips = new ArrayList<>();
}
