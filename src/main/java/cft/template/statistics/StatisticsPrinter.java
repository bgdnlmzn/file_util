package cft.template.statistics;

import java.util.Map;

public class StatisticsPrinter {
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
                    System.out.println("Minimum length: " + stats.getMinLength());
                    System.out.println("Maximum length: " + stats.getMaxLength());
                } else {
                    System.out.println("Minimum: " + stats.getMin());
                    System.out.println("Maximum: " + stats.getMax());
                    System.out.println("Sum: " + stats.getSum());
                    System.out.println("Avg: " + stats.getAverage());
                }
            }
            System.out.println("-------------------");
        }
    }
}