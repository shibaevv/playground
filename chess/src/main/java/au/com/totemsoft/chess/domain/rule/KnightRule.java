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
        addContinue(new Position(position, -2, 1), piece, pieces, moves);
        addContinue(new Position(position, -1, 2), piece, pieces, moves);
        // left down
        addContinue(new Position(position, -2, -1), piece, pieces, moves);
        addContinue(new Position(position, -1, -2), piece, pieces, moves);
        // right up
        addContinue(new Position(position, 2, 1), piece, pieces, moves);
        addContinue(new Position(position, 1, 2), piece, pieces, moves);
        // right down
        addContinue(new Position(position, 2, -1), piece, pieces, moves);
        addContinue(new Position(position, 1, -2), piece, pieces, moves);
        //
        return moves;
    }

    @Override
    protected boolean addContinue(Position move, Piece piece, List<Piece> pieces, Set<Position> moves) {
        MoveTypeEnum moveType = moveType(move, piece, pieces); 
        if (moveType == null) {
            return false;
        }
        moves.add(move);
        // ignore MoveTypeEnum.CAPTURE
        return true;
    }

}
