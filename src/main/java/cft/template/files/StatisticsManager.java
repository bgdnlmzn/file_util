package cft.template.files;

import cft.template.statistics.Statistics;
import cft.template.statistics.StatisticsCollector;

import java.io.IOException;
import java.io.BufferedWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class StatisticsManager {
    private final StatisticsCollector intStatsCollector = new StatisticsCollector();
    private final StatisticsCollector floatStatsCollector = new StatisticsCollector();
    private final StatisticsCollector stringStatsCollector = new StatisticsCollector();

    public void collectStatistics(String line, BufferedWriter intWriter, BufferedWriter floatWriter, BufferedWriter stringWriter) throws IOException {
        if (isInteger(line)) {
            writeLine(intWriter, line, intStatsCollector);
        } else if (isFloat(line)) {
            writeLine(floatWriter, line, floatStatsCollector);
        } else {
            writeLine(stringWriter, line, stringStatsCollector);
        }
    }

    public Map<String, Statistics> getAllStatistics() {
        Map<String, Statistics> statsMap = new HashMap<>();
        statsMap.put("integers", intStatsCollector.getStatistics());
        statsMap.put("floats", floatStatsCollector.getStatistics());
        statsMap.put("strings", stringStatsCollector.getStatistics());
        return statsMap;
    }

    private boolean isInteger(String line) {
        try {
            new BigInteger(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String line) {
        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void writeLine(BufferedWriter writer,
                           String line,
                           StatisticsCollector collector)
            throws IOException {
        writer.write(line);
        writer.newLine();
        collector.add(line);
    }
}
