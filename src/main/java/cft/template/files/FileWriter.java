package cft.template.files;

import cft.template.statistics.Statistics;
import cft.template.statistics.StatisticsCollector;

import java.io.IOException;
import java.io.BufferedWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для записи данных в файлы и сбора статистики по типам данных.
 * Используется в {@link FileManager}.
 */
public class FileWriter {
    private final StatisticsCollector intStatsCollector = new StatisticsCollector();
    private final StatisticsCollector floatStatsCollector = new StatisticsCollector();
    private final StatisticsCollector stringStatsCollector = new StatisticsCollector();

    private BufferedWriter intWriter = null;
    private BufferedWriter floatWriter = null;
    private BufferedWriter stringWriter = null;

    /**
     * Обрабатывает строку данных и записывает её в соответствующий файл в зависимости от типа данных.
     *
     * @param line Строка данных для обработки.
     * @param fileHandler Обработчик файлов.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
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

    /**
     * Возвращает статистику по всем обработанным данным.
     *
     * @return Словарь статистики по типам данных.
     */
    public Map<String, Statistics> getAllStatistics() {
        Map<String, Statistics> statsMap = new HashMap<>();
        statsMap.put("integers", intStatsCollector.getStatistics());
        statsMap.put("floats", floatStatsCollector.getStatistics());
        statsMap.put("strings", stringStatsCollector.getStatistics());
        return statsMap;
    }

    /**
     * Проверяет, является ли строка целым числом.
     *
     * @param line Строка для проверки.
     * @return {@code true}, если строка является целым числом, иначе {@code false}.
     */
    private static boolean isInteger(String line) {
        try {
            new BigInteger(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли строка числом с плавающей точкой.
     *
     * @param line Строка для проверки.
     * @return {@code true}, если строка является числом с плавающей точкой, иначе {@code false}.
     */
    private static boolean isFloat(String line) {
        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Записывает строку данных в файл и обновляет статистику.
     *
     * @param writer {@code BufferedWriter} для записи данных.
     * @param line Строка данных для записи.
     * @param collector Коллектор статистики для обновления.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    private void writeLine(BufferedWriter writer,
                           String line,
                           StatisticsCollector collector)
            throws IOException {
        writer.write(line);
        writer.newLine();
        collector.add(line);
    }

    /**
     * Закрывает все открытые потоки записи.
     */
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