package au.com.totemsoft.chess.domain;

import au.com.totemsoft.chess.domain.rule.BishopRule;
import au.com.totemsoft.chess.domain.rule.KingRule;
import au.com.totemsoft.chess.domain.rule.KnightRule;
import au.com.totemsoft.chess.domain.rule.Move;
import au.com.totemsoft.chess.domain.rule.PawnRule;
import au.com.totemsoft.chess.domain.rule.QueenRule;
import au.com.totemsoft.chess.domain.rule.RookRule;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@AllArgsConstructor
public enum TypeEnum {

    K("King"),
    Q("Queen"),
    R("Rook"),
    B("Bishop"),
    N("Knight"),
    P("Pawn");

    private final String name;

    public Move getMove() {
        switch (this) {
        case K:
            return new KingRule();
        case Q:
            return new QueenRule();
        case R:
            return new RookRule();
        case B:
            return new BishopRule();
        case N:
            return new KnightRule();
        case P:
            return new PawnRule();
        default:
            throw new IllegalArgumentException();
        }
    }

}
