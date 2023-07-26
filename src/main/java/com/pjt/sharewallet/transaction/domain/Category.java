package com.pjt.sharewallet.transaction.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Category {

    @Id
    @Column(name = "CATEGORY_CODE")
    private int code;

    private String name;
}
