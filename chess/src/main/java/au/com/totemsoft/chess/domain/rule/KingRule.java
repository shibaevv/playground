package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;

import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public class KingRule extends AbstractRule {

    @Override
    public Set<Position> validMoves(Piece piece, List<Piece> pieces) {
        throw new IllegalArgumentException("Not implemented yet");
    }

}
