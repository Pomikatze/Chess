package service;

import enums.FigureNameEnum;
import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.Board;
import model.Ceil;
import model.figure.api.Figure;
import model.player.api.Player;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class GameService {

    public void doPlay(Scanner scanner, Player player1, Player player2, Board board) {
        Map<Player, List<Figure>> figures = board.getFigures(player1, player2);
        List<Figure> figures1 = figures.get(player1);
        List<Figure> figures2 = figures.get(player2);

        Figure king1 = figures1.stream()
                .filter(figure -> FigureNameEnum.KING.getMnemonic().equals(figure.getMnemonic()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("На доске нет короля у " + player1));
        Figure king2 = figures2.stream()
                .filter(figure -> FigureNameEnum.KING.getMnemonic().equals(figure.getMnemonic()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("На доске нет короля у " + player2));
        System.out.println(board);

        while (true) {
            if (!king1.isAlive()) {
                player2.setWinner(true);
                break;
            }
            while (true) {
                boolean warn = doMove(scanner, player1, board);
                if (!warn) {
                    break;
                }
            }

            if (!king2.isAlive()) {
                player1.setWinner(true);
                break;
            }
            while (true) {
                boolean warn = doMove(scanner, player2, board);
                if (!warn) {
                    break;
                }
            }
        }
    }

    private boolean doMove(Scanner scanner, Player player, Board board) {
        System.out.println("Ход игрока " + player.getName());
        System.out.println(board.drawBoard());
        System.out.println("Выберите фигуру (введите координату в формате 'B1'):");

        String figure1 = doScan(scanner, board).toUpperCase(Locale.ROOT);
        String[] split = figure1.split("");

        if (split.length != 2) {
            System.out.println("Ошибка! Неверный формат координаты");
            return true;
        }

        String coordinate = split[0] + "." + split[1] + ".";
        List<Figure> figures = board.getFiguresByPlayer(player);
        Figure takeFigure = figures.stream()
                .filter(figure -> figure.getCoordinate().equals(coordinate))
                .findFirst()
                .orElse(null);

        if (takeFigure == null) {
            System.out.println("Не удалось найти фигуру с такими координатами. Попробуйте ещё");
            return true;
        }

        System.out.println(takeFigure);
        System.out.println(takeFigure.getPossibleMoves(board));
        System.out.println("Куда сделать ход? (введите координату в формате 'B1'): ");

        String toCeil = doScan(scanner, board).toUpperCase(Locale.ROOT);
        String[] splitCeil = toCeil.split("");

        if (splitCeil.length != 2) {
            System.out.println("Ошибка! Неверный формат координаты");
            return true;
        }
        int lengthValue = LengthValueEnum.getOrderByValue(splitCeil[0] + ".");
        int heightValue = HeightValueEnum.getOrderByValue(splitCeil[1] + ".");

        if (heightValue == -1 || lengthValue == -1) {
            System.out.println("Не удалось найти клетку с такими координатами. Попробуйте ещё");
            return true;
        }
        List<Ceil> possibleCells = takeFigure.getPossibleCells(board);
        Ceil[][] cells = board.getCells();
        Ceil ceil = cells[heightValue][lengthValue];

        if (!possibleCells.contains(ceil)) {
            System.out.println("Сюда ходить нельзя. Попробуйте ещё раз");
            return true;
        }

        takeFigure.doMoveOnCeil(ceil, board);
        return false;
    }

    private String doScan(Scanner scanner, Board board) {
        String response;

        while (true) {
            String next = scanner.nextLine();
            String lower = next.toLowerCase(Locale.ROOT);

            if (lower.equals("exit")) {
                System.out.println("Игра окончена!");
                System.exit(0);
            } else if (lower.equals("board")) {
                System.out.println(board);
                System.out.println(board.drawBoard());
                continue;
            } else if (lower.startsWith("figure")) {
                String substring = next.toUpperCase().substring(next.length() - 2);
                String[] split = substring.split("");
                Ceil[][] cells = board.getCells();
                if (split.length != 2) {
                    System.out.println("Ошибка! Неверный формат координаты");
                    continue;
                }
                int lengthValue = LengthValueEnum.getOrderByValue(split[0] + ".");
                int heightValue = HeightValueEnum.getOrderByValue(split[1] + ".");

                if (heightValue == -1 || lengthValue == -1) {
                    System.out.println("Не удалось найти клетку с такими координатами. Попробуйте ещё");
                    continue;
                }
                Ceil ceil = cells[heightValue][lengthValue];
                Figure figure = ceil.getFigure();

                if (figure == null) {
                    System.out.println("Тут нет никакой фигуры. Попробуйте ещё");
                } else {
                    System.out.println(figure);
                    System.out.println(figure.getPossibleMoves(board));
                }
                continue;
            } else if (lower.equals("help")) {
                System.out.println("Команды:\nexit - выход из игры\n" +
                        "board - вывести доску на экран\n" +
                        "figure ** - где ** - координаты фигуры - выводит информацию о фигуре\n" +
                        "Координаты вводить в формате 'B4'. " +
                        "Сначала буква латиницей, затем цифра. Без пробелов и других символов. Буквы можно строчные и заглавыне.\n");
                continue;
            }
            response = next;
            break;
        }

        return response;
    }
}
