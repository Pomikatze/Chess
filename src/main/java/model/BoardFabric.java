package model;


import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.figure.api.Figure;
import model.figure.impl.*;
import model.player.api.Player;

public final class BoardFabric {

    private static final int CLASSIC_LENGTH = 8;
    private static final int CLASSIC_HEIGHT = 8;

    public Board createEmptyClassicBoard(Player player1, Player player2) {
        return new Board(CLASSIC_HEIGHT, CLASSIC_LENGTH, player1, player2);
    }

    public Board createClassicBoard(Player player1, Player player2) {
        Ceil[][] cells = new Ceil[CLASSIC_HEIGHT][CLASSIC_LENGTH];

        for (int i = 0; i < CLASSIC_HEIGHT; i++) {
            for (int j = 0; j < CLASSIC_LENGTH; j++) {
                Ceil ceil = new Ceil(HeightValueEnum.getByOrder(i), LengthValueEnum.getByOrder(j));
                cells[i][j] = ceil;

                if (i == 0) {
                    if (j == 0 || j == 7) {
                        Figure bishop = new Bishop(ceil, player1);
                        ceil.setFigure(bishop);
                        continue;
                    }
                    if (j == 1 || j == 6) {
                        Figure knight = new Knight(ceil, player1);
                        ceil.setFigure(knight);
                        continue;
                    }
                    if (j == 2 || j == 5) {
                        Figure rook = new Rook(ceil, player1);
                        ceil.setFigure(rook);
                        continue;
                    }
                    if (j == 3) {
                        Figure king = new King(ceil, player1);
                        ceil.setFigure(king);
                        continue;
                    }

                    Figure queen = new Queen(ceil, player1);
                    ceil.setFigure(queen);
                    continue;
                }

                if (i == 1) {
                    Figure pawn = new Pawn(ceil, player1);
                    ceil.setFigure(pawn);
                    continue;

                }

                if (i == 6) {
                    Figure pawn = new Pawn(ceil, player2);
                    ceil.setFigure(pawn);
                    continue;
                }

                if (i == 7) {
                    if (j == 0 || j == 7) {
                        Figure bishop = new Bishop(ceil, player2);
                        ceil.setFigure(bishop);
                        continue;
                    }
                    if (j == 1 || j == 6) {
                        Figure knight = new Knight(ceil, player2);
                        ceil.setFigure(knight);
                        continue;
                    }
                    if (j == 2 || j == 5) {
                        Figure rook = new Rook(ceil, player2);
                        ceil.setFigure(rook);
                        continue;
                    }
                    if (j == 4) {
                        Figure king = new King(ceil, player2);
                        ceil.setFigure(king);
                        continue;
                    }

                    Figure queen = new Queen(ceil, player2);
                    ceil.setFigure(queen);
                }
            }
        }

        return new Board(cells, player1, player2);
    }
}
