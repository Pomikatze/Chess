package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление имён игровых фигур.
 */
@Getter
@RequiredArgsConstructor
public enum FigureNameEnum {

//    PAWN("Пешка", "\u2659"),
//    KNIGHT("Конь", "\u2658"),
//    KING("Король", "\u2654"),
//    QUEEN("Королева", "\u2655"),
//    BISHOP("Слон", "\u2657"),
//    ROOK("Ладья", "\u2656");

    PAWN("Пешка", "P."),
    KNIGHT("Конь", "L."),
    KING("Король", "K."),
    QUEEN("Королева", "Q."),
    BISHOP("Слон", "B."),
    ROOK("Ладья", "R.");

    private final String name;
    private final String mnemonic;
}
