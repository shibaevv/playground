package au.com.totemsoft.chess.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Piece {

    private final ColorEnum color;

    private final TypeEnum type;

    private final Position position;

    @Override
    public String toString() {
        return type + " on " + position; // TODO: use color
    }

}
