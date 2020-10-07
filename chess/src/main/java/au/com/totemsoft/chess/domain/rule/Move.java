package au.com.totemsoft.chess.domain.rule;

import java.util.List;
import java.util.Set;

import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;

public interface Move {

    Set<Position> validMoves(Piece piece, List<Piece> pieces);

}
