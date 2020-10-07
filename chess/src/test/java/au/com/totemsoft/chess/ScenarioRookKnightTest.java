package au.com.totemsoft.chess;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import au.com.totemsoft.chess.domain.ColorEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;
import au.com.totemsoft.chess.domain.TypeEnum;

public class ScenarioRookKnightTest extends BaseScenarioTest {

    @Test
    public void findValidMoves_2() {
        List<Piece> pieces = Stream.of(
            new Piece(ColorEnum.W, TypeEnum.R, Position.of("f2")),
            new Piece(ColorEnum.W, TypeEnum.N, Position.of("f6"))
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
        // R on f2: [a2, b2, c2, d2, e2, f1, f3, f4, f5, g2, h2]
        // N on f6: [d5, d7, e4, e8, g4, g8, h5, h7]
    }

    @Test @Ignore
    public void findValidMoves_2_diffColor() {
        List<Piece> pieces = Stream.of(
            new Piece(ColorEnum.W, TypeEnum.R, Position.of("f2")),
            new Piece(ColorEnum.B, TypeEnum.N, Position.of("f6"))
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
    }

    @Test
    public void findValidMoves_3() {
        List<Piece> pieces = Stream.of(
            new Piece(ColorEnum.W, TypeEnum.R, Position.of("e3")),
            new Piece(ColorEnum.B, TypeEnum.R, Position.of("e6")),
            new Piece(ColorEnum.B, TypeEnum.N, Position.of("d6"))
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
        // R on e3: [a3, b3, c3, d3, e1, e2, e4, e5, e6, f3, g3, h3]
        // R on e6: [e3, e4, e5, e7, e8, f6, g6, h6]
        // N on d6: [b5, b7, c4, c8, e4, e8, f5, f7]
    }

}
