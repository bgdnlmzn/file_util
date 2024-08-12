package cft.template.files;

import cft.template.statistics.Statistics;
import cft.template.statistics.StatisticsCollector;

import java.io.IOException;
import java.io.BufferedWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FileWriter {
    private final StatisticsCollector intStatsCollector = new StatisticsCollector();
    private final StatisticsCollector floatStatsCollector = new StatisticsCollector();
    private final StatisticsCollector stringStatsCollector = new StatisticsCollector();

    private BufferedWriter intWriter = null;
    private BufferedWriter floatWriter = null;
    private BufferedWriter stringWriter = null;

    public void writingProcess(String line, FileHandler fileHandler) throws IOException {
        if (isInteger(line)) {
            if (intWriter == null) {
                intWriter = fileHandler.createWriter("integers.txt");
            }
            writeLine(intWriter, line, intStatsCollector);
        } else if (isFloat(line)) {
            if (floatWriter == null) {
                floatWriter = fileHandler.createWriter("floats.txt");
            }
            writeLine(floatWriter, line, floatStatsCollector);
        } else {
            if (stringWriter == null) {
                stringWriter = fileHandler.createWriter("strings.txt");
            }
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

    public void closeWriters() {
        try {
            if (intWriter != null) intWriter.close();
            if (floatWriter != null) floatWriter.close();
            if (stringWriter != null) stringWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}