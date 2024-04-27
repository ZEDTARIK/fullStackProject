package org.ettarak.api.enums;

import lombok.Data;
import lombok.Getter;
@Getter
public enum Level {
    HIGH (3),
    MEDIUM (2),
    LOWER (1);
    private  final  int level;
    Level(int level) {
        this.level = level;
    }
}
