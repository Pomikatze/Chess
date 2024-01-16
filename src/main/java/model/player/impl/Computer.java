package model.player.impl;

/**
 * Игрок-компьютер.
 */
public final class Computer extends AbstractPlayer {

    public Computer(boolean white) {
        super("computer", true, white);
    }
}
