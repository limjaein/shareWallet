package com.pjt.sharewallet.member.domain;

import lombok.Getter;
import lombok.val;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String description;

    Role(String description) {
        this.description = description;
    }

}
