package cft.template.statistics;

/**
 * Класс для хранения и вычисления статистики по данным.
 */
public class Statistics {
    private int count;
    private Double min;
    private Double max;
    private double sum;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    /**
     * Добавляет числовое значение в статистику, обновляя значения минимума, максимума и суммы.
     *
     * @param value Числовое значение для добавления.
     */
    public void add(double value) {
        if (count == 0) {
            min = value;
            max = value;
        } else {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        sum += value;
        count++;
    }

    /**
     * Добавляет строковое значение в статистику, обновляя максимальную и минимальную длину строки.
     *
     * @param value Строковое значение для добавления.
     */
    public void add(String value) {
        int length = value.length();
        if (length < minLength) {
            minLength = length;
        }
        if (length > maxLength) {
            maxLength = length;
        }
        count++;
    }

    /**
     * Возвращает количество обработанных значений.
     *
     * @return Количество значений.
     */
    public int getCount() {
        return count;
    }

    /**
     * Возвращает минимальное числовое значение.
     *
     * @return Минимальное значение, или {@code null}, если значения отсутствуют.
     */
    public Double getMin() {
        return min;
    }

    /**
     * Возвращает максимальное числовое значение.
     *
     * @return Максимальное значение, или {@code null}, если значения отсутствуют.
     */
    public Double getMax() {
        return max;
    }

    /**
     * Возвращает сумму всех числовых значений.
     *
     * @return Сумма числовых значений.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Возвращает среднее значение всех числовых данных.
     *
     * @return Среднее значение.
     */
    public double getAverage() {
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /**
     * Возвращает минимальную длину строки.
     *
     * @return Минимальная длина строки.
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Возвращает максимальную длину строки.
     *
     * @return Максимальная длина строки.
     */
    public int getMaxLength() {
        return maxLength;
    }
}