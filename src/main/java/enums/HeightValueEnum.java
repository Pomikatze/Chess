package enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Перечисление координат высоты шахматной доски.
 */
@Getter
@RequiredArgsConstructor
public enum HeightValueEnum {
    ONE("1.", 0),
    TWO("2.", 1),
    THREE("3.", 2),
    FOUR("4.", 3),
    FIVE("5.", 4),
    SIX("6.", 5),
    SEVEN("7.", 6),
    EIGHT("8.", 7);

    private final String value;
    private final int order;

    /**
     * Получить координату по положению в массиве.
     *
     * @param order положение в массиве
     * @return координата высоты
     */
    public static HeightValueEnum getByOrder(int order) {
        return Arrays.stream(HeightValueEnum.values())
                .filter(item -> order == item.getOrder())
                .findFirst()
                .orElse(null);
    }

    /**
     * Получить мнемонику по положению в массиве.
     *
     * @param order положение в массиве
     * @return мнемоника координаты
     */
    public static String getValueByOrder(int order) {
        HeightValueEnum valueEnum = getByOrder(order);

        if (valueEnum == null) {
            return null;
        }
        return valueEnum.getValue();
    }

    public static int getOrderByValue(String value) {
        HeightValueEnum heightValueEnum = Arrays.stream(HeightValueEnum.values())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElse(null);

        if (heightValueEnum == null) {
            return -1;
        } else {
            return heightValueEnum.getOrder();
        }
    }
}
