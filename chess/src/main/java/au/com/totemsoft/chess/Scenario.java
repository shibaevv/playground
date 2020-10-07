package au.com.totemsoft.chess;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import au.com.totemsoft.chess.domain.Board;
import au.com.totemsoft.chess.domain.ColorEnum;
import au.com.totemsoft.chess.domain.Piece;
import au.com.totemsoft.chess.domain.Position;
import au.com.totemsoft.chess.domain.TypeEnum;
import au.com.totemsoft.chess.domain.rule.Move;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Scenario {

    private final List<Piece> pieces;

    public Map<Piece, Set<Position>> findValidMoves() {
        Map<Piece, Set<Position>> result = new LinkedHashMap<>();
        for (Piece piece : pieces) {
            result.put(piece, findValidMoves(piece));
        }
        return result;
    }

    private Set<Position> findValidMoves(Piece piece) {
        final Move move = piece.getType().getMove();
        return move.validMoves(piece, pieces);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            while (true) {
                final Map<Piece, Set<Position>> validMoves = new Scenario(pieces(scanner)).findValidMoves();
                Board.print(validMoves);
                //
                System.out.print("Continue (Y/N)?: ");
                String result = scanner.next().toUpperCase();
                if (!result.equals("Y")) {
                    break;
                }
                System.out.println();
            }
        }
        //
        System.exit(0);
    }

    private static List<Piece> pieces(Scanner scanner) {
        List<Piece> pieces = new ArrayList<>();
        System.out.println("> FindValidMoves");
        System.out.print("Enter number of pieces: ");
        int count = scanner.nextInt();
        System.out.println();
        for (int i = 1; i <= count; i++) {
            System.out.println("Piece " + i);
            //
            System.out.print("Enter colour (W/B): ");
            ColorEnum color = ColorEnum.valueOf(scanner.next().toUpperCase());
            System.out.print("Enter type (R/B/N): ");
            TypeEnum type = TypeEnum.valueOf(scanner.next().toUpperCase());
            System.out.print("Enter position: ");
            String position = scanner.next().toLowerCase();
            //
            pieces.add(new Piece(color, type, Position.of(position)));
            //
            System.out.println();
        }
        return pieces;
    }

}
