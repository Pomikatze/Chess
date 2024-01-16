package model.figure.api;

import model.Board;
import model.Ceil;
import model.player.api.Player;

import java.util.List;

/**
 * Апи шахматной фигуры.
 */
public interface Figure {

    /**
     * Находится ли фигура ещё на доске.
     *
     * @return true - да / false - нет
     */
    boolean isAlive();

    /**
     * Установить находится ли фигура на доске.
     *
     * @param alive true - да / false - нет
     */
    void setAlive(boolean alive);

    /**
     * Присвоить клетку шахматной доски.
     *
     * @param ceil клетка шахматной доски
     */
    void setCeil(Ceil ceil);

    /**
     * Получить имя фигуры.
     *
     * @return имя фигуры
     */
    String getName();

    /**
     * Получить игрока владеющего фигурой.
     *
     * @return игрок владеющий фигурой
     */
    Player getPlayer();

    /**
     * Получить мнемонику фигуры.
     *
     * @return мнемоника фигуры
     */
    String getMnemonic();

    /**
     * Получить координаты фигуры на доске.
     *
     * @return координаты
     */
    String getCoordinate();

    /**
     * Получить строковое представление возможных ходов.
     *
     * @param board информация о доске
     * @return строковое представление возможных ходов
     */
    String getPossibleMoves(Board board);

    /**
     * Получить список возможных ходов.
     *
     * @param board информация о доске
     * @return список возможных ходов
     */
    List<Ceil> getPossibleCells(Board board);

    /**
     * Сделать ход на указанную клетку.
     *
     * @param ceil клетка, куда пойти
     * @param board информация о доске
     */
    void doMoveOnCeil(Ceil ceil, Board board);
}
