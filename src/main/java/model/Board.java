package model;

import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.figure.api.Figure;
import model.player.api.Player;

import java.util.*;
import java.util.stream.IntStream;

public final class Board {

    private static final String EMPTY_SPACE = "..";
    private static final String EMPTY_SPACE_DOUBLE = "....";
    private static final String EMPTY_CEIL = "*.";
    private static final String RED_COLOUR = "\u001B[31m";
    private static final String BLUE_COLOUR = "\u001B[34m";
    private static final String RESET_COLOUR = "\u001B[0m";

    private final Ceil[][] cells;
    private final int height;
    private final int length;
    private final List<Player> players;

    public Board(int height, int length, Player player1, Player player2) {
        if (height <= 0 || length <= 0) {
            throw new IllegalArgumentException("Невозможно создать доску с высотой или длинной меньше или равной 0");
        }

        Ceil[][] cells = new Ceil[length][height];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Ceil(HeightValueEnum.getByOrder(j), LengthValueEnum.getByOrder(i));
            }
        }

        this.height = height;
        this.length = length;
        this.cells = cells;
        this.players = List.of(player1, player2);
    }

    public Board(Ceil[][] cells, Player player1, Player player2) {
        int length = cells.length;
        int height = cells[0].length;

        for (Ceil[] cell : cells) {
            if (cell.length != cells.length) {
                throw new IllegalArgumentException("Доска должна быть квадратной!");
            }
        }

        if (height == 0) {
            throw new IllegalArgumentException("Невозможно создать доску с высотой или длинной меньше или равной 0");
        }

        this.height = height;
        this.length = length;
        this.cells = cells;
        this.players = List.of(player1, player2);
    }

    public String drawBoard() {
        StringBuilder builder = new StringBuilder();
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        for (int i = 0; i < cells.length + 4; i++) {

            if (i == 0 || i == cells.length + 3) {
                builder.append(EMPTY_SPACE_DOUBLE);
                Arrays.stream(LengthValueEnum.values())
                        .forEach(item -> builder.append(item.getValue()));
                builder.append(EMPTY_SPACE_DOUBLE);
                builder.append("\n");
                continue;
            } else if (i == 1 || i == cells.length + 2) {
                IntStream.range(0, cells.length + 4)
                        .forEach(o -> builder.append(EMPTY_SPACE));
                builder.append("\n");
                continue;
            }

            for (int j = 0; j < cells[i - 2].length + 4; j++) {

                if (j == 0 || j == cells[i - 2].length + 3) {
                    String value = HeightValueEnum.getValueByOrder(i - 2);

                    if (value == null) {
                        builder.append(EMPTY_SPACE);
                    } else {
                        builder.append(value);
                    }
                    continue;
                } else if (j == 1 || j == cells[i - 2].length + 2) {
                    builder.append(EMPTY_SPACE);
                    continue;
                }

                Figure figure = cells[i - 2][j - 2].getFigure();

                if (figure == null) {
                    builder.append(EMPTY_CEIL);
                } else {
                    String mnemonic = figure.getMnemonic();
                    if (figure.getPlayer() == player1) {
                        builder.append(RED_COLOUR);
                    } else if (figure.getPlayer() == player2) {
                        builder.append(BLUE_COLOUR);
                    } else {
                        throw new IllegalArgumentException("У фигуры не назначен игрок! " + figure);
                    }
                    builder.append(mnemonic);
                    builder.append(RESET_COLOUR);
                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    public Map<Player, List<Figure>> getFigures(Player player1, Player player2) {
        Map<Player, List<Figure>> response = new HashMap<>();
        response.put(player1, getFiguresByPlayer(player1));
        response.put(player2, getFiguresByPlayer(player2));
        return response;
    }

    public List<Figure> getFiguresByPlayer(Player player) {
        List<Figure> response = new ArrayList<>();

        for (Ceil[] cell : this.cells) {
            for (Ceil ceil : cell) {
                Figure figure = ceil.getFigure();

                if (figure != null) {
                    Player figurePlayer = figure.getPlayer();

                    if (player.equals(figurePlayer)) {
                        response.add(figure);
                    }
                }
            }
        }
        return response;
    }

    public Ceil[][] getCells() {
        return this.cells;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return "Доска {" +
                "высота = " + this.height +
                ", длинна = " + this.length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return height == board.height && length == board.length && Arrays.deepEquals(cells, board.cells);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(height, length);
        result = 31 * result + Arrays.deepHashCode(cells);
        return result;
    }
}
