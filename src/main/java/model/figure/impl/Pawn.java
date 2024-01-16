package model.figure.impl;

import enums.FigureNameEnum;
import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.Board;
import model.Ceil;
import model.figure.api.Figure;
import model.player.api.Player;

import java.util.List;

public final class Pawn extends AbstractFigure {

    private boolean isFirstMove = true;

    public Pawn(Ceil ceil, Player player) {
        super(FigureNameEnum.PAWN.getName(), ceil, FigureNameEnum.PAWN.getMnemonic(), player);
    }

    @Override
    protected List<Ceil> fillResponse(List<Ceil> response, Ceil[][] cells, HeightValueEnum height, LengthValueEnum length) {
        int lengthOrder = length.getOrder();
        int heightOrder = height.getOrder();

        if (!this.player.isWhite() && isNotUpBorder(height)) {
            Ceil upCeil = cells[heightOrder - 1][lengthOrder];
            Figure upFigure = upCeil.getFigure();

            if (upFigure == null) {
                response.add(upCeil);
            }

            if (isFirstMove) {
                response.add(cells[heightOrder - 2][lengthOrder]);
            }

            if (isNotLeftBorder(length)) {
                Ceil ceil = cells[heightOrder - 1][lengthOrder - 1];
                Figure figure = ceil.getFigure();

                if (figure != null && !figure.getPlayer().equals(this.player)) {
                    response.add(ceil);
                }
            }
            if (isNotRightBorder(length)) {
                Ceil ceil = cells[heightOrder - 1][lengthOrder + 1];
                Figure figure = ceil.getFigure();

                if (figure != null && !figure.getPlayer().equals(this.player)) {
                    response.add(ceil);
                }
            }
        }

        if (this.player.isWhite() && isNotDownBorder(height)) {
            Ceil downCeil = cells[heightOrder + 1][lengthOrder];
            Figure downFigure = downCeil.getFigure();

            if (downFigure == null) {
                response.add(downCeil);
            }

            if (isFirstMove) {
                response.add(cells[heightOrder + 2][lengthOrder]);
            }

            if (isNotLeftBorder(length)) {
                Ceil ceil = cells[heightOrder + 1][lengthOrder - 1];
                Figure figure = ceil.getFigure();

                if (figure != null && !figure.getPlayer().equals(this.player)) {
                    response.add(ceil);
                }
            }
            if (isNotRightBorder(length)) {
                Ceil ceil = cells[heightOrder + 1][lengthOrder + 1];
                Figure figure = ceil.getFigure();

                if (figure != null && !figure.getPlayer().equals(this.player)) {
                    response.add(ceil);
                }
            }
        }

        return filterCellsByFriendlyFigure(response);
    }

    @Override
    public void doMoveOnCeil(Ceil ceil, Board board) {
        super.doMoveOnCeil(ceil, board);

        if (this.isFirstMove) {
            this.isFirstMove = false;
        }

        if (ceil.getHeight().equals(HeightValueEnum.EIGHT) || ceil.getHeight().equals(HeightValueEnum.ONE)) {
            System.out.println("Пешка игрока " + this.player.getName() + " превращается в ферзя!");
            Figure queen = new Queen(ceil, this.player);
            this.ceil = null;
            this.alive = false;
            ceil.setFigure(queen);
            List<Figure> figures = board.getFiguresByPlayer(this.player);
            figures.add(queen);
        }
    }
}
