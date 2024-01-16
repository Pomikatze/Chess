package model.figure.impl;

import enums.FigureNameEnum;
import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.Ceil;
import model.player.api.Player;

import java.util.List;

public final class Bishop extends AbstractFigure {

    public Bishop(Ceil ceil, Player player) {
        super(FigureNameEnum.BISHOP.getName(), ceil, FigureNameEnum.BISHOP.getMnemonic(), player);
    }

    @Override
    protected List<Ceil> fillResponse(List<Ceil> response, Ceil[][] cells, HeightValueEnum height, LengthValueEnum length) {
        int lengthOrder = length.getOrder();
        int heightOrder = height.getOrder();

        if (isNotUpBorder(height)) {
            for (int i = heightOrder - 1; i >= 0; i--) {
                Ceil ceil = cells[i][lengthOrder];
                response.add(ceil);

                if (ceil.getFigure() != null) {
                    break;
                }
            }
        }

        if (isNotDownBorder(height)) {
            for (int i = heightOrder + 1; i < cells.length; i++) {
                Ceil ceil = cells[i][lengthOrder];
                response.add(ceil);

                if (ceil.getFigure() != null) {
                    break;
                }
            }
        }

        if (isNotLeftBorder(length)) {
            for (int i = lengthOrder - 1; i >= 0; i--) {
                Ceil ceil = cells[heightOrder][i];
                response.add(ceil);

                if (ceil.getFigure() != null) {
                    break;
                }
            }
        }

        if (isNotRightBorder(length)) {
            for (int i = lengthOrder + 1; i < cells[0].length; i++) {
                Ceil ceil = cells[heightOrder][i];
                response.add(ceil);

                if (ceil.getFigure() != null) {
                    break;
                }
            }
        }

        return filterCellsByFriendlyFigure(response);
    }
}
