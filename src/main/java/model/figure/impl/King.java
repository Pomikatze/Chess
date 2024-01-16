package model.figure.impl;

import enums.FigureNameEnum;
import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.Ceil;
import model.player.api.Player;

import java.util.List;

public final class King extends AbstractFigure {

    public King(Ceil ceil, Player player) {
        super(FigureNameEnum.KING.getName(), ceil, FigureNameEnum.KING.getMnemonic(), player);
    }

    @Override
    protected List<Ceil> fillResponse(List<Ceil> response, Ceil[][] cells, HeightValueEnum height, LengthValueEnum length) {
        int lengthOrder = length.getOrder();
        int heightOrder = height.getOrder();

        if (isNotUpBorder(height)) {
            response.add(cells[heightOrder - 1][lengthOrder]);

            if (isNotLeftBorder(length)) {
                response.add(cells[heightOrder - 1][lengthOrder - 1]);
            }
            if (isNotRightBorder(length)) {
                response.add(cells[heightOrder - 1][lengthOrder + 1]);
            }
        }

        if (isNotDownBorder(height)) {
            response.add(cells[heightOrder + 1][lengthOrder]);

            if (isNotLeftBorder(length)) {
                response.add(cells[heightOrder + 1][lengthOrder - 1]);
            }
            if (isNotRightBorder(length)) {
                response.add(cells[heightOrder + 1][lengthOrder + 1]);
            }
        }

        if (isNotLeftBorder(length)) {
            response.add(cells[heightOrder][lengthOrder - 1]);
        }

        if (isNotRightBorder(length)) {
            response.add(cells[heightOrder][lengthOrder + 1]);
        }

        return filterCellsByFriendlyFigure(response);
    }
}
