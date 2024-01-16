import model.Board;
import model.BoardFabric;
import model.player.api.Player;
import model.player.impl.Human;
import service.GameService;

import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        GameService gameService = new GameService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру шахматы!");
        System.out.println("Введите имя первого игрока:");

        String name1 = scanner.nextLine();
        Player player1 = new Human(name1, true);

        System.out.println("Введите имя второго игрока:");

        String name2 = scanner.nextLine();
        Player player2 = new Human(name2, false);

        System.out.println("Игроки:\nСверху:\n" + player1 + "\nСнизу:\n" + player2);

        BoardFabric fabric = new BoardFabric();
        Board board = fabric.createClassicBoard(player1, player2);

        gameService.doPlay(scanner, player1, player2, board);

        System.out.println("Игра окончена! Победитель:");
        if (player1.isWinner()) {
            System.out.println(player1.getName());
        } else if (player2.isWinner()) {
            System.out.println(player2.getName());
        } else {
            throw new IllegalArgumentException("Не удалось определить победителя(");
        }
    }
}
