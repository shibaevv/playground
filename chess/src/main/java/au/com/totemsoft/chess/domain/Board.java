package au.com.totemsoft.chess.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A chessboard comprises a 8x8 grid.
 * Squares on the board are notated using a letter and a number,
 * where the letter indicates the column (or "file") and the number indicates the row (or "rank").
 */
public class Board {

    public static final Character[] COLUMNS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public static final int[] ROWS = {1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * @param column - 1..8
     * @return
     */
    public static Character column(int column) {
        return COLUMNS[column - 1];
    }

    /**
     * @param column - a..h
     * @return
     */
    public static int column(Character column) {
        return Arrays.binarySearch(COLUMNS, column.charValue()) + 1;
    }

    public static void print(Map<Piece, Set<Position>> validMoves) {
        System.out.println("Valid moves");
        for (Iterator<Entry<Piece, Set<Position>>> i = validMoves.entrySet().iterator(); i.hasNext(); ) {
            final Entry<Piece, Set<Position>> e = i.next();
            final Piece piece = e.getKey();
            final Set<Position> moves = e.getValue();
            System.out.println(
                String.format("%s: %s", piece, moves)
            );
        }
    }

}
