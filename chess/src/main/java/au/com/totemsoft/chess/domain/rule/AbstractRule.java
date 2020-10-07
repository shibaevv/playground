package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;

import au.com.totemsoft.chess.domain.MoveTypeEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public abstract class AbstractRule implements Move {

    //private Set<Position> positions(List<Piece> pieces) {
    //    return pieces.stream().map(Piece::getPosition).collect(Collectors.toSet());
    //}

    protected MoveTypeEnum moveType(Position move, Piece piece, List<Piece> pieces) {
        // validate column/row valid
        if (!move.isValid()) {
            return null;
        }
        // validate blocked pieces (same color)
        final Piece blocked = pieces.stream()
            .filter(p -> move.equals(p.getPosition()))
            .findAny()
            .orElse(null);
        if (blocked != null) {
            // same color
            if (piece.getColor() == blocked.getColor()) {
                return null;
            }
            return MoveTypeEnum.CAPTURE;
        }
        //
        return MoveTypeEnum.MOVE;
    }

    protected boolean addContinue(Position move, Piece piece, List<Piece> pieces, Set<Position> moves) {
        MoveTypeEnum moveType = moveType(move, piece, pieces); 
        if (moveType == null) {
            return false;
        }
        moves.add(move);
        if (moveType == MoveTypeEnum.CAPTURE) {
            return false;
        }
        return true;
    }

}
