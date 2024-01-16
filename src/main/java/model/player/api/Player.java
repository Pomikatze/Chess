package model.player.api;

/**
 * Апи игрока.
 */
public interface Player {

    /**
     * Является ли игрок компьютером.
     *
     * @return true - да / false - нет
     */
    boolean isComputer();

    /**
     * Какими пешками играет игрок. Чаще всего первый игрок играет белыми (находятся наверху).
     *
     * @return true - белые \ black - чёрные
     */
    boolean isWhite();

    /**
     * Установить значение победителя.
     *
     * @param winner true - да / false - нет
     */
    void setWinner(boolean winner);

    /**
     * Является ли игрок победителем.
     *
     * @return true - да / false - нет
     */
    boolean isWinner();

    /**
     * Получить имя игрока.
     *
     * @return имя игрока
     */
    String getName();
}
