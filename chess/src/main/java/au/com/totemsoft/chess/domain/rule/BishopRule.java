package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
            if (!addContinue(new Position(position, offset, -offset), piece, pieces, moves)) {
                break;
            }
        }
        for (int c = column - 1; c > 0; c--) {
            int offset = c - column;
            if (!addContinue(new Position(position, offset, offset), piece, pieces, moves)) {
                break;
            }
        }
        // right up/down
        for (int c = column + 1; c <= 8; c++) {
            int offset = c - column;
            if (!addContinue(new Position(position, offset, -offset), piece, pieces, moves)) {
                break;
            }
        }
        for (int c = column + 1; c <= 8; c++) {
            int offset = c - column;
            if (!addContinue(new Position(position, offset, offset), piece, pieces, moves)) {
                break;
            }
        }
        //
        return moves;
    }

}
