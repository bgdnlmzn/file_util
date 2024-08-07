package cft.template.statistics;

import java.util.Map;

public class StatisticsPrinter {
    public static void printStatistics(Map<String, Statistics> statsMap,
                                       boolean showShortStats,
                                       boolean showFullStats) {
        for (Map.Entry<String, Statistics> entry : statsMap.entrySet()) {
            System.out.println("Statistics for " + entry.getKey() + ":");
            Statistics stats = entry.getValue();
            System.out.println("Amount: " + stats.getCount());
            if (showFullStats) {
                if (entry.getKey().equals("strings")) {
                    System.out.println("Minimum length: " + stats.getMinLength());
                    System.out.println("Maximum length: " + stats.getMaxLength());
                }
                else {
                    System.out.println("Minimum: " + stats.getMin());
                    System.out.println("Maximum: " + stats.getMax());
                    System.out.println("Sum: " + stats.getSum());
                    System.out.println("Avg: " + stats.getAverage());
                }
            }
        }
    }
}