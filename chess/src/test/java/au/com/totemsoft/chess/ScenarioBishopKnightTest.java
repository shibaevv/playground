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

public class ScenarioBishopKnightTest extends BaseScenarioTest {

    @Test
    public void findValidMoves_2() {
        Piece bishopWhite = new Piece(ColorEnum.W, TypeEnum.B, Position.of("g5"));
        Piece knightWhite = new Piece(ColorEnum.W, TypeEnum.N, Position.of("f6"));
        List<Piece> pieces = Stream.of(
            bishopWhite,
            knightWhite
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
        // B on g5: [c1, d2, e3, f4, h4, h6]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("c1"),
                Position.of("d2"),
                Position.of("e3"),
                Position.of("f4"),
                Position.of("h4"),
                Position.of("h6")},
            validMoves.get(bishopWhite).toArray(new Position[0]));
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
        Piece bishopWhite = new Piece(ColorEnum.W, TypeEnum.B, Position.of("g5"));
        Piece knightBlack = new Piece(ColorEnum.B, TypeEnum.N, Position.of("f6"));
        List<Piece> pieces = Stream.of(
            bishopWhite,
            knightBlack
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
    }

    @Test
    public void findValidMoves_3() {
        Piece bishopWhite = new Piece(ColorEnum.B, TypeEnum.B, Position.of("d1"));
        Piece knightBlack = new Piece(ColorEnum.B, TypeEnum.N, Position.of("e2"));
        Piece knightWhite = new Piece(ColorEnum.W, TypeEnum.N, Position.of("b3"));
        List<Piece> pieces = Stream.of(
            bishopWhite,
            knightBlack,
            knightWhite
        ).collect(Collectors.toList());
        Map<Piece, Set<Position>> validMoves = findValidMoves(pieces);
        // B on d1: [b3, c2]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("b3"),
                Position.of("c2")},
            validMoves.get(bishopWhite).toArray(new Position[0]));
        // N on e2: [c1, c3, d4, f4, g1, g3]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("c1"),
                Position.of("c3"),
                Position.of("d4"),
                Position.of("f4"),
                Position.of("g1"),
                Position.of("g3")},
            validMoves.get(knightBlack).toArray(new Position[0]));
        // N on b3: [a1, a5, c1, c5, d2, d4]
        Assert.assertArrayEquals(
            new Position[] {
                Position.of("a1"),
                Position.of("a5"),
                Position.of("c1"),
                Position.of("c5"),
                Position.of("d2"),
                Position.of("d4")},
            validMoves.get(knightWhite).toArray(new Position[0]));
    }

}
