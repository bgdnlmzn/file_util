package cft.template.manager;

import cft.template.statistics.Statistics;
import cft.template.statistics.StatisticsCollector;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {
    private final String outputPath;
    private final String prefix;
    private final boolean append;

    private final StatisticsCollector intStatsCollector = new StatisticsCollector();
    private final StatisticsCollector floatStatsCollector = new StatisticsCollector();
    private final StatisticsCollector stringStatsCollector = new StatisticsCollector();

    private BufferedWriter intWriter;
    private BufferedWriter floatWriter;
    private BufferedWriter stringWriter;

    public FileManager(String outputPath, String prefix, boolean append) {
        if (outputPath.equals(".")) {
            this.outputPath = System.getProperty("user.dir");
        } else {
            this.outputPath = outputPath;
        }
        this.prefix = prefix;
        this.append = append;

        File dir = new File(this.outputPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            intWriter = new BufferedWriter(new FileWriter(getOutputFile("integers.txt"), append));
            floatWriter = new BufferedWriter(new FileWriter(getOutputFile("floats.txt"), append));
            stringWriter = new BufferedWriter(new FileWriter(getOutputFile("strings.txt"), append));
        } catch (IOException e) {
            System.err.println("Ошибка при создании файлов для записи: " + e.getMessage());
        }
    }

    public void manageFiles(List<String> fileNames) {
        for (String fileName : fileNames) {
            manageFile(new File(fileName));
        }

        try {
            if (intWriter != null) intWriter.close();
            if (floatWriter != null) floatWriter.close();
            if (stringWriter != null) stringWriter.close();
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии файлов для записи: " + e.getMessage());
        }
    }

    private void manageFile(File file) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                checkLine(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + file.getName() + ": " + e.getMessage());
        }
    }

    private void checkLine(String line) {
        try {
            if (isInteger(line)) {
                writeLine(intWriter, line, intStatsCollector);
            } else if (isFloat(line)) {
                writeLine(floatWriter, line, floatStatsCollector);
            } else {
                writeLine(stringWriter, line, stringStatsCollector);
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи строки: " + e.getMessage());
        }
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

    private File getOutputFile(String fileName) {
        String path = outputPath.isEmpty() ? "" : outputPath + File.separator;
        return new File(path + prefix + fileName);
    }

    private void writeLine(BufferedWriter writer, String line, StatisticsCollector collector) throws IOException {
        writer.write(line);
        writer.newLine();
        collector.add(line);
    }

    public Map<String, Statistics> getAllStatistics() {
        Map<String, Statistics> statsMap = new HashMap<>();
        statsMap.put("integers", intStatsCollector.getStatistics());
        statsMap.put("floats", floatStatsCollector.getStatistics());
        statsMap.put("strings", stringStatsCollector.getStatistics());
        return statsMap;
    }
}
