package au.com.totemsoft.chess;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import au.com.totemsoft.chess.domain.ColorEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;
import au.com.totemsoft.chess.domain.TypeEnum;

public class ScenarioRookKnightTest extends BaseScenarioTest {

    @Test
    public void findValidMoves_2() {
        Piece rookWhite = new Piece(ColorEnum.W, TypeEnum.R, Position.of("f2"));
        Piece knightWhite = new Piece(ColorEnum.W, TypeEnum.N, Position.of("f6"));
        List<Piece> pieces = Stream.of(
            rookWhite,
            knightWhite
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
        // R on f2: [a2, b2, c2, d2, e2, f1, f3, f4, f5, g2, h2]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("a2"),
                Position.of("b2"),
                Position.of("c2"),
                Position.of("d2"),
                Position.of("e2"),
                Position.of("f1"),
                Position.of("f3"),
                Position.of("f4"),
                Position.of("f5"),
                Position.of("g2"),
                Position.of("h2")},
            validMoves.get(rookWhite).toArray(new Position[0]));
        // N on f6: [d5, d7, e4, e8, g4, g8, h5, h7]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("d5"),
                Position.of("d7"),
                Position.of("e4"),
                Position.of("e8"),
                Position.of("g4"),
                Position.of("g8"),
                Position.of("h5"),
                Position.of("h7")},
            validMoves.get(knightWhite).toArray(new Position[0]));
    }

    @Test @Ignore
    public void findValidMoves_2_diffColor() {
        Piece rookWhite = new Piece(ColorEnum.W, TypeEnum.R, Position.of("f2"));
        Piece knightBlack = new Piece(ColorEnum.B, TypeEnum.N, Position.of("f6"));
        List<Piece> pieces = Stream.of(
            rookWhite,
            knightBlack
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
    }

    @Test
    public void findValidMoves_3() {
        Piece rookWhite = new Piece(ColorEnum.W, TypeEnum.R, Position.of("e3"));
        Piece rookBlack = new Piece(ColorEnum.B, TypeEnum.R, Position.of("e6"));
        Piece knightBlack = new Piece(ColorEnum.B, TypeEnum.N, Position.of("d6"));
        List<Piece> pieces = Stream.of(
            rookWhite,
            rookBlack,
            knightBlack
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
        // R on e3: [a3, b3, c3, d3, e1, e2, e4, e5, e6, f3, g3, h3]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("a3"),
                Position.of("b3"),
                Position.of("c3"),
                Position.of("d3"),
                Position.of("e1"),
                Position.of("e2"),
                Position.of("e4"),
                Position.of("e5"),
                Position.of("e6"),
                Position.of("f3"),
                Position.of("g3"),
                Position.of("h3")},
            validMoves.get(rookWhite).toArray(new Position[0]));
        // R on e6: [e3, e4, e5, e7, e8, f6, g6, h6]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("e3"),
                Position.of("e4"),
                Position.of("e5"),
                Position.of("e7"),
                Position.of("e8"),
                Position.of("f6"),
                Position.of("g6"),
                Position.of("h6")},
            validMoves.get(rookBlack).toArray(new Position[0]));
        // N on d6: [b5, b7, c4, c8, e4, e8, f5, f7]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("b5"),
                Position.of("b7"),
                Position.of("c4"),
                Position.of("c8"),
                Position.of("e4"),
                Position.of("e8"),
                Position.of("f5"),
                Position.of("f7")},
            validMoves.get(knightBlack).toArray(new Position[0]));
    }

}
