package cft.template.files;

import cft.template.statistics.Statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileManager {
    private final OutputFileHandler outputFileHandler;
    private final StatisticsManager statisticsManager;
    private final CharsetDetectorService charsetDetectorService;

    public FileManager(String outputPath, String prefix, boolean append) {
        this.outputFileHandler = new OutputFileHandler(outputPath, prefix, append);
        this.statisticsManager = new StatisticsManager();
        this.charsetDetectorService = new CharsetDetectorService();
    }

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

        outputFileHandler.closeWriters();

        System.out.println("Done!");
    }

    private void manageFile(File file) {
        try (BufferedReader reader = outputFileHandler.getReader(file,
                charsetDetectorService.detectCharset(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                statisticsManager.collectStatistics(line,
                        outputFileHandler.getIntWriter(),
                        outputFileHandler.getFloatWriter(),
                        outputFileHandler.getStringWriter());
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + e.getMessage());
        }
    }

    public Map<String, Statistics> getAllStatistics() {
        return statisticsManager.getAllStatistics();
    }
}