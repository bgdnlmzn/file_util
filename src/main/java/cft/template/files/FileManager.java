package cft.template.files;

import cft.template.statistics.Statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileManager {
    private final FileHandler fileHandler;
    private final FileWriter fileWriter;
    private final CharsetDetectorService charsetDetectorService;

    public FileManager(String outputPath, String prefix, boolean append) {
        this.fileHandler = new FileHandler(outputPath, prefix, append);
        this.fileWriter = new FileWriter();
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

        fileWriter.closeWriters();
        System.out.println("-------------------");
        System.out.println("Done!");
        System.out.println("-------------------");
    }

    private void manageFile(File file) {
        try (BufferedReader reader = fileHandler.getReader(file,
                charsetDetectorService.detectCharset(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileWriter.writingProcess(line, fileHandler);
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + e.getMessage());
        }
    }

    public Map<String, Statistics> getAllStatistics() {
        return fileWriter.getAllStatistics();
    }
}