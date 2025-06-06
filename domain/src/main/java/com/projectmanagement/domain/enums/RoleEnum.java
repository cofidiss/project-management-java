package com.projectmanagement.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum RoleEnum {
    READ(1 << 0),    // 0001 → 1
    ADD(1 << 1),     // 0010 → 2
    UPDATE(1 << 2),  // 0100 → 4
    DELETE(1 << 3);  // 1000 → 8

    private final int bit;

    RoleEnum(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public static List<RoleEnum> fromMask(int mask) {
        List<RoleEnum> roles = new ArrayList<>();
        for (RoleEnum role : RoleEnum.values()) {
            if ((mask & role.getBit()) != 0) {
                roles.add(role);
            }
        }
        return roles;
    }
}
