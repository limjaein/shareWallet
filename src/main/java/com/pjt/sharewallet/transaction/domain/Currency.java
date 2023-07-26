package com.pjt.sharewallet.transaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @Column(name = "CURRENCY_CODE")
    private String code;

    private String name;

    private String symbol;

    private float currency;

    private String update_time;
}
