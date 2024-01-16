package model;


import enums.HeightValueEnum;
import enums.LengthValueEnum;
import model.figure.api.Figure;

import java.util.Objects;

public final class Ceil {

    private final HeightValueEnum HEIGHT;
    private final LengthValueEnum LENGTH;

    private Figure figure;

    public Ceil(HeightValueEnum height, LengthValueEnum length) {
        this.HEIGHT = height;
        this.LENGTH = length;
    }

    public HeightValueEnum getHeight() {
        return this.HEIGHT;
    }

    public LengthValueEnum getLength() {
        return this.LENGTH;
    }

    @Override
    public String toString() {
        return "" + this.LENGTH.getValue() + this.HEIGHT.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ceil)) return false;
        Ceil ceil = (Ceil) o;
        return HEIGHT == ceil.HEIGHT && LENGTH == ceil.LENGTH;
    }

    @Override
    public int hashCode() {
        return Objects.hash(HEIGHT, LENGTH);
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
