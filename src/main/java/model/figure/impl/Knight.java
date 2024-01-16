package model.figure.impl;

import enums.FigureNameEnum;
import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.Ceil;
import model.player.api.Player;

import java.util.List;

public final class Knight extends AbstractFigure {

    public Knight(Ceil ceil, Player player) {
        super(FigureNameEnum.KNIGHT.getName(), ceil, FigureNameEnum.KNIGHT.getMnemonic(), player);
    }

    @Override
    protected boolean isNotUpBorder(HeightValueEnum height) {
        return !(HeightValueEnum.ONE.equals(height) || HeightValueEnum.TWO.equals(height));
    }

    @Override
    protected boolean isNotDownBorder(HeightValueEnum height) {
        return !(HeightValueEnum.EIGHT.equals(height) || HeightValueEnum.SEVEN.equals(height));
    }

    @Override
    protected boolean isNotLeftBorder(LengthValueEnum length) {
        return !(LengthValueEnum.A.equals(length) || LengthValueEnum.B.equals(length));
    }

    @Override
    protected boolean isNotRightBorder(LengthValueEnum length) {
        return !(LengthValueEnum.H.equals(length) || LengthValueEnum.G.equals(length));
    }

    @Override
    protected List<Ceil> fillResponse(List<Ceil> response, Ceil[][] cells, HeightValueEnum height, LengthValueEnum length) {
        int lengthOrder = length.getOrder();
        int heightOrder = height.getOrder();

        if (isNotUpBorder(height)) {
            if (lengthOrder != 0) {
                response.add(cells[heightOrder - 2][lengthOrder - 1]);
            }
            if (lengthOrder != 7) {
                response.add(cells[heightOrder - 2][lengthOrder + 1]);
            }
        }

        if (isNotDownBorder(height)) {
            if (lengthOrder != 0) {
                response.add(cells[heightOrder + 2][lengthOrder - 1]);
            }
            if (lengthOrder != 7) {
                response.add(cells[heightOrder + 2][lengthOrder + 1]);
            }
        }

        if (isNotLeftBorder(length)) {
            if (heightOrder != 0) {
                response.add(cells[heightOrder - 1][lengthOrder - 2]);
            }
            if (heightOrder != 7) {
                response.add(cells[heightOrder + 1][lengthOrder - 2]);
            }
        }

        if (isNotRightBorder(length)) {
            if (heightOrder != 0) {
                response.add(cells[heightOrder - 1][lengthOrder + 2]);
            }
            if (heightOrder != 7) {
                response.add(cells[heightOrder + 1][lengthOrder + 2]);
            }
        }

        return filterCellsByFriendlyFigure(response);
    }
}
