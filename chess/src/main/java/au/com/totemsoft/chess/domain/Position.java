package au.com.totemsoft.chess.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Position implements Comparable<Position> {

    private final int column;

    private final int row;

    public static Position of(String position) {
        return new Position(position);
    }

    public Position(String position) {
        this(
            Character.valueOf(position.charAt(0)),
            Integer.parseInt(String.valueOf(position.charAt(1)))
        );
    }

    public Position(Character column, int row) {
        this(Board.column(column), row);
    }

    public Position(Position p, int columnOffset, int rowOffset) {
        this(p.column + columnOffset, p.row + rowOffset);
    }

    /**
     * @param column - 1..8
     * @param row - 1..8
     */
    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * 
     * @return column - 1..8 and row - 1..8
     */
    public boolean isValid() {
        return column > 0 && column <= 8 && row > 0 && row <= 8;
    }

    public String name() {
        return Board.column(column) + "" + row;
    }

    @Override
    public String toString() {
        return name();
    }

    @Override
    public int compareTo(Position p) {
        return name().compareTo(p.name());
    }

}
