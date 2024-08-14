package cft.template.statistics;

import java.util.Map;

/**
 * Класс для вывода статистики в консоль.
 */
public class StatisticsPrinter {
    /**
     * Печатает статистику по данным в консоль в зависимости от флагов.
     *
     * @param statsMap Словарь статистики по типам данных.
     * @param showShortStats Флаг для отображения краткой статистики.
     * @param showFullStats Флаг для отображения полной статистики.
     */
    public static void printStatistics(Map<String, Statistics> statsMap,
                                       boolean showShortStats,
                                       boolean showFullStats) {
        if (!showShortStats && !showFullStats) {
            return;
        }

        for (Map.Entry<String, Statistics> entry : statsMap.entrySet()) {
            System.out.println("Statistics for added " + entry.getKey() + ":");
            Statistics stats = entry.getValue();
            System.out.println("Amount: " + stats.getCount());
            if (showFullStats) {
                if ("strings".equals(entry.getKey())) {
                    System.out.println("Min length: " + stats.getMinLength());
                    System.out.println("Max length: " + stats.getMaxLength());
                } else {
                    System.out.println("Min: " + stats.getMin());
                    System.out.println("Max: " + stats.getMax());
                    System.out.println("Sum: " + stats.getSum());
                    System.out.println("Avg: " + stats.getAverage());
                }
            }
            System.out.println("-------------------");
        }
    }
}