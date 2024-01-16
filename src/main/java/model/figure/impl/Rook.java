package model.figure.impl;

import enums.FigureNameEnum;
import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.Ceil;
import model.player.api.Player;

import java.util.List;

public final class Rook extends AbstractFigure {

    public Rook(Ceil ceil, Player player) {
        super(FigureNameEnum.ROOK.getName(), ceil, FigureNameEnum.ROOK.getMnemonic(), player);
    }

    @Override
    protected List<Ceil> fillResponse(List<Ceil> response, Ceil[][] cells, HeightValueEnum height, LengthValueEnum length) {
        int lengthOrder = length.getOrder();
        int heightOrder = height.getOrder();

        if (isNotUpBorder(height)) {
            if (isNotLeftBorder(length)) {
                int i = 0;

                while (true) {
                    i++;
                    int heightCoordinate = heightOrder - i;
                    int lengthCoordinate = lengthOrder - i;

                    if (heightCoordinate < 0 || lengthCoordinate < 0) {
                        break;
                    }

                    Ceil ceil = cells[heightCoordinate][lengthCoordinate];
                    response.add(ceil);

                    if (ceil.getFigure() != null) {
                        break;
                    }
                }
            }
            if (isNotRightBorder(length)) {
                int i = 0;

                while (true) {
                    i++;
                    int heightCoordinate = heightOrder - i;
                    int lengthCoordinate = lengthOrder + i;

                    if (heightCoordinate < 0 || lengthCoordinate > cells[0].length) {
                        break;
                    }

                    Ceil ceil = cells[heightCoordinate][lengthCoordinate];
                    response.add(ceil);

                    if (ceil.getFigure() != null) {
                        break;
                    }
                }
            }
        }

        if (isNotDownBorder(height)) {
            if (isNotLeftBorder(length)) {
                int i = 0;

                while (true) {
                    i++;
                    int heightCoordinate = heightOrder + i;
                    int lengthCoordinate = lengthOrder - i;

                    if (heightCoordinate > cells.length || lengthCoordinate < 0) {
                        break;
                    }

                    Ceil ceil = cells[heightCoordinate][lengthCoordinate];
                    response.add(ceil);

                    if (ceil.getFigure() != null) {
                        break;
                    }
                }
            }
            if (isNotRightBorder(length)) {
                int i = 0;

                while (true) {
                    i++;
                    int heightCoordinate = heightOrder + i;
                    int lengthCoordinate = lengthOrder + i;

                    if (heightCoordinate > cells.length || lengthCoordinate > cells[0].length) {
                        break;
                    }

                    Ceil ceil = cells[heightCoordinate][lengthCoordinate];
                    response.add(ceil);

                    if (ceil.getFigure() != null) {
                        break;
                    }
                }
            }
        }

        return filterCellsByFriendlyFigure(response);
    }
}
