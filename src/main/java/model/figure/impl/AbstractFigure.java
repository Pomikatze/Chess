package model.figure.impl;

import enums.HeightValueEnum;
import enums.LengthValueEnum;
import lombok.Data;
import model.Board;
import model.Ceil;
import model.figure.api.Figure;
import model.player.api.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractFigure implements Figure {

    protected final String name;
    protected final String mnemonic;
    protected final Player player;

    protected Ceil ceil;
    protected boolean alive;

    protected AbstractFigure(String name, Ceil ceil, String mnemonic, Player player) {
        this.name = name;
        this.ceil = ceil;
        this.mnemonic = mnemonic;
        this.alive = true;
        this.player = player;
    }

    @Override
    public String getCoordinate() {
        if (this.ceil == null) {
            return "Фигура не на доске";
        }
        return this.ceil.toString();
    }

    @Override
    public String getPossibleMoves(Board board) {
        return "Возможные клетки для хода:\n" + getPossibleCells(board).toString();
    }

    @Override
    public void doMoveOnCeil(Ceil ceil, Board board) {
        List<Ceil> possibleCells = getPossibleCells(board);

        if (!possibleCells.contains(ceil)) {
            throw new IllegalArgumentException("Сюда ходить нельзя");
        }
        this.getCeil().setFigure(null);
        this.setCeil(ceil);
        Figure enemy = ceil.getFigure();

        if (enemy != null) {
            List<Figure> figuresByEnemy = board.getFiguresByPlayer(enemy.getPlayer());
            figuresByEnemy.remove(enemy);
            enemy.setAlive(false);
            enemy.setCeil(null);
            ceil.setFigure(this);
            System.out.println(this.getName() + " игрока " + this.player.getName() + " съедает " +
                    enemy.getName() + " игрока " + enemy.getPlayer().getName());
        }

        ceil.setFigure(this);
    }

    @Override
    public List<Ceil> getPossibleCells(Board board) {
        List<Ceil> response = new ArrayList<>();
        Ceil[][] cells = board.getCells();
        Ceil ceil = this.getCeil();

        HeightValueEnum height = ceil.getHeight();
        LengthValueEnum length = ceil.getLength();

        return fillResponse(response, cells, height, length);
    }

    /**
     * Определить находится ли фигура у верхней границы.
     * @param height координата высоты фигуры на доске
     * @return true - да / false - нет
     */
    protected boolean isNotUpBorder(HeightValueEnum height) {
        return !HeightValueEnum.ONE.equals(height);
    }

    /**
     * Определить находится ли фигура у нижней границы.
     * @param height координата высоты фигуры на доске
     * @return true - да / false - нет
     */
    protected boolean isNotDownBorder(HeightValueEnum height) {
        return !HeightValueEnum.EIGHT.equals(height);
    }

    /**
     * Определить находится ли фигура у левой границы.
     * @param length координата длины фигуры на доске
     * @return true - да / false - нет
     */
    protected boolean isNotLeftBorder(LengthValueEnum length) {
        return !LengthValueEnum.A.equals(length);
    }

    /**
     * Определить находится ли фигура у правой границы.
     * @param length координата длины фигуры на доске
     * @return true - да / false - нет
     */
    protected boolean isNotRightBorder(LengthValueEnum length) {
        return !LengthValueEnum.H.equals(length);
    }

    /**
     * Заполнить список возможных ходов.
     *
     * @param response сам список
     * @param cells массив клеток на доске
     * @param heightOrder положение фигуры по высоте
     * @param lengthOrder положение фигуры по длине
     * @return обновлённый список
     */
    protected abstract List<Ceil> fillResponse(List<Ceil> response, Ceil[][] cells, HeightValueEnum heightOrder, LengthValueEnum lengthOrder);

    /**
     * Убрать из списка возможных ходов те, где находятся дружественные фигуры.
     *
     * @param cells список возможных ходов
     * @return обновлённый список
     */
    protected List<Ceil> filterCellsByFriendlyFigure(List<Ceil> cells) {
        List<Ceil> response = new ArrayList<>();

        for (Ceil ceil : cells) {
            Figure figure = ceil.getFigure();

            if (figure == null || figure.getPlayer() != this.getPlayer()) {
                response.add(ceil);
            }
        }
        return response;
    }

    @Override
    public String toString() {
        return "Фигура " + name + " на клетке с координатами " + ceil.toString();
    }
}
