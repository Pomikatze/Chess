package model.player.impl;

import lombok.Data;
import model.player.api.Player;

/**
 * Абстрактная сущность игрока.
 */
@Data
public abstract class AbstractPlayer implements Player {

    private final String name;
    private final boolean computer;
    private boolean winner;
    private boolean white;

    protected AbstractPlayer(String name, boolean computer, boolean white) {
        this.name = name;
        this.computer = computer;
        this.winner = false;
        this.white = white;
    }

    @Override
    public String toString() {
        return "Игрок " + this.name + (isComputer() ? " " : " не ") + "является компьютером";
    }
}
