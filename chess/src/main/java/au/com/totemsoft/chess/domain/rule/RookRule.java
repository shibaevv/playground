package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import au.com.totemsoft.chess.domain.MoveTypeEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public class RookRule extends AbstractRule {

    @Override
    public Set<Position> validMoves(Piece piece, List<Piece> pieces) {
        // current position
        final Position position = piece.getPosition();
        final int column = position.getColumn();
        final int row = position.getRow();
        final Set<Position> moves = new TreeSet<>();
        // left
        for (int c = column - 1; c > 0; c--) {
            int offset = c - column;
            Position p = new Position(position, offset, 0);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        // up
        for (int r = row - 1; r > 0; r--) {
            int offset = r - row;
            Position p = new Position(position, 0, offset);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        // right
        for (int c = column + 1; c <= 8; c++) {
            int offset = c - column;
            Position p = new Position(position, offset, 0);
            MoveTypeEnum moveType = moveType(p, piece, pieces); 
            if (moveType == null) {
                break;
            }
            moves.add(p);
            if (moveType == MoveTypeEnum.CAPTURE) {
                break;
            }
        }
        // down
        for (int r = row + 1; r <= 8; r++) {
            int offset = r - row;
            Position p = new Position(position, 0, offset);
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
