package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import au.com.totemsoft.chess.domain.MoveTypeEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public class KnightRule extends AbstractRule {

    @Override
    public Set<Position> validMoves(Piece piece, List<Piece> pieces) {
        // current position
        final Position position = piece.getPosition();
        final Set<Position> moves = new TreeSet<>();
        // left up
        add(new Position(position, -2, 1), piece, pieces, moves);
        add(new Position(position, -1, 2), piece, pieces, moves);
        // left down
        add(new Position(position, -2, -1), piece, pieces, moves);
        add(new Position(position, -1, -2), piece, pieces, moves);
        // right up
        add(new Position(position, 2, 1), piece, pieces, moves);
        add(new Position(position, 1, 2), piece, pieces, moves);
        // right down
        add(new Position(position, 2, -1), piece, pieces, moves);
        add(new Position(position, 1, -2), piece, pieces, moves);
        //
        return moves;
    }

    private void add(Position p, Piece piece, List<Piece> pieces, Set<Position> moves) {
        MoveTypeEnum moveType = moveType(p, piece, pieces); 
        if (moveType != null) {
            moves.add(p);
        }
    }

}
