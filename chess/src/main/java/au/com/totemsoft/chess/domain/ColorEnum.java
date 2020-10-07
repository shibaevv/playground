package au.com.totemsoft.chess.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorEnum {

    W("White"),
    B("Black");

    private final String name;

}
