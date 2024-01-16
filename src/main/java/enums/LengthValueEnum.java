package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Перечисление координат длины шахматной доски.
 */
@Getter
@RequiredArgsConstructor
public enum LengthValueEnum {
    A("A.", 0),
    B("B.", 1),
    C("C.", 2),
    D("D.", 3),
    E("E.", 4),
    F("F.", 5),
    G("G.", 6),
    H("H.", 7);

    private final String value;
    private final int order;

    /**
     * Получить координату по положению в массиве.
     *
     * @param order положение в массиве
     * @return координата длины
     */
    public static LengthValueEnum getByOrder(int order) {
        return Arrays.stream(LengthValueEnum.values())
                .filter(item -> order == item.getOrder())
                .findFirst()
                .orElse(null);
    }

    /**
     * Получить положение в массиве по мнемонике.
     *
     * @param value мнемоника координаты
     * @return положение в массиве
     */
    public static int getOrderByValue(String value) {
        LengthValueEnum lengthValueEnum = Arrays.stream(LengthValueEnum.values())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElse(null);

        if (lengthValueEnum == null) {
            return -1;
        } else {
            return lengthValueEnum.getOrder();
        }
    }
}
