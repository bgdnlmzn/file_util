package cft.template.files;

import cft.template.statistics.Statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Класс для управления файлами: чтение данных и сохранение статистики.
 * Использует {@link FileHandler}, {@link FileWriter} и {@link CharsetDetectorService}.
 */
public class FileManager {
    private final FileHandler fileHandler;
    private final FileWriter fileWriter;

    /**
     * Конструктор для класса.
     * Создает экземпляр {@code FileManager} с заданными параметрами.
     *
     * @param outputPath Путь к директории для сохранения выходных файлов.
     * @param prefix Префикс для имен выходных файлов.
     * @param append Флаг, указывающий на необходимость добавления содержимого в существующий файл.
     */
    public FileManager(String outputPath, String prefix, boolean append) {
        this.fileHandler = new FileHandler(outputPath, prefix, append);
        this.fileWriter = new FileWriter();
    }

    /**
     * Обрабатывает список файлов, считывая их содержимое и записывая данные в соответствующие файлы.
     *
     * @param fileNames Список имен файлов для обработки.
     */
    public void manageFiles(List<String> fileNames) {
        if (fileNames == null || fileNames.isEmpty()) {
            System.out.println("No files to process!");
            return;
        }

        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File not found: " + fileName);
                continue;
            }
            if (file.length() == 0) {
                System.out.println("File is empty: " + fileName);
                continue;
            }

            manageFile(file);
        }

        fileWriter.closeWriters();
        System.out.println("-------------------");
        System.out.println("Done!");
        System.out.println("-------------------");
    }

    /**
     * Обрабатывает один файл, считывая его содержимое и записывая данные в соответствующие файлы.
     *
     * @param file Файл для обработки.
     */
    private void manageFile(File file) {
        try (BufferedReader reader = fileHandler.getReader(file,
                CharsetDetectorService.detectCharset(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileWriter.writingProcess(line, fileHandler);
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + e.getMessage());
        }
    }

    /**
     * Возвращает статистику по всем обработанным файлам.
     *
     * @return Словарь статистики по типам данных.
     */
    public Map<String, Statistics> getAllStatistics() {
        return fileWriter.getAllStatistics();
    }
}