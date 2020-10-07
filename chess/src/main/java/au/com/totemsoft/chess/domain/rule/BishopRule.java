package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import au.com.totemsoft.chess.domain.MoveTypeEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public class BishopRule extends AbstractRule {

    @Override
    public Set<Position> validMoves(Piece piece, List<Piece> pieces) {
        // current position
        final Position position = piece.getPosition();
        final int column = position.getColumn();
        final Set<Position> moves = new TreeSet<>();
        // left up/down
        for (int c = column - 1; c > 0; c--) {
            int offset = c - column;
            Position p = new Position(position, offset, -offset);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        for (int c = column - 1; c > 0; c--) {
            int offset = c - column;
            Position p = new Position(position, offset, offset);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        // right up/down
        for (int c = column + 1; c <= 8; c++) {
            int offset = c - column;
            Position p = new Position(position, offset, -offset);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        for (int c = column + 1; c <= 8; c++) {
            int offset = c - column;
            Position p = new Position(position, offset, offset);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        //
        return moves;
    }

}
