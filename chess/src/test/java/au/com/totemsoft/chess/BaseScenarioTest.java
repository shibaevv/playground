package au.com.totemsoft.chess;

import java.util.List;
import java.util.Map;
import java.util.Set;

import au.com.totemsoft.chess.domain.Board;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public abstract class BaseScenarioTest {

    protected Map<Piece, Set<Position>> findValidMoves(List<Piece> pieces) {
        final Map<Piece, Set<Position>> validMoves = new Scenario(pieces).findValidMoves();
        Board.print(validMoves);
        return validMoves;
    }

}
