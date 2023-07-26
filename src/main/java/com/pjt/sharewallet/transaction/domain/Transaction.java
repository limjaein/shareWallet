package com.pjt.sharewallet.transaction.domain;

import com.pjt.sharewallet.member.domain.Member;
import com.pjt.sharewallet.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String title;

    private String content;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

}
