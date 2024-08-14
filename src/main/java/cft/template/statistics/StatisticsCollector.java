package cft.template.statistics;

/**
 * Класс для сбора и управления статистикой по данным.
 */
public class StatisticsCollector {
    private final Statistics statistics = new Statistics();

    /**
     * Добавляет значение в статистику.
     *
     * @param value Строковое значение для добавления.
     */
    public void add(String value) {
        try {
            double numericValue = Double.parseDouble(value);
            statistics.add(numericValue);
        } catch (NumberFormatException e) {
            statistics.add(value);
        }
    }

    /**
     * Возвращает собранную статистику.
     *
     * @return Статистика по данным.
     */
    public Statistics getStatistics() {
        return statistics;
    }
}