package cft.template.manager;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import cft.template.statistics.Statistics;
import cft.template.statistics.StatisticsCollector;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
    }

    public void manageFiles(List<String> fileNames) {
        for (String fileName : fileNames) {
            manageFile(new File(fileName));
        }

        closeWriters();

        System.out.println("Done!");
    }

    private void manageFile(File file) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), detectCharset(file))) {
            String line = reader.readLine();
            if (line == null) {
                System.err.println("Warning: file " + file.getName() + " is empty");
                return;
            }

            do {
                checkLine(line);
            } while ((line = reader.readLine()) != null);

        } catch (IOException e) {
            System.err.println("Error reading file " + e.getMessage());
        }
    }


    private void checkLine(String line) {
        try {
            if (isInteger(line)) {
                writeLine(getIntWriter(), line, intStatsCollector);
            } else if (isFloat(line)) {
                writeLine(getFloatWriter(), line, floatStatsCollector);
            } else {
                writeLine(getStringWriter(), line, stringStatsCollector);
            }
        } catch (IOException e) {
            System.err.println("Error writing line: " + e.getMessage());
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

    private BufferedWriter getIntWriter() throws IOException {
        if (intWriter == null) {
            intWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(getOutputFile("integers.txt"), append),
                    StandardCharsets.UTF_8
                    )
            );
        }
        return intWriter;
    }

    private BufferedWriter getFloatWriter() throws IOException {
        if (floatWriter == null) {
            floatWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(getOutputFile("floats.txt"), append),
                    StandardCharsets.UTF_8
                    )
            );
        }
        return floatWriter;
    }

    private BufferedWriter getStringWriter() throws IOException {
        if (stringWriter == null) {
            stringWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(getOutputFile("strings.txt"), append),
                    StandardCharsets.UTF_8
                    )
            );
        }
        return stringWriter;
    }

    private File getOutputFile(String fileName) {
        return new File(outputPath
                + File.separator
                + prefix
                + fileName);
    }

    private void writeLine(BufferedWriter writer,
                           String line,
                           StatisticsCollector collector)
            throws IOException {
        writer.write(line);
        writer.newLine();
        collector.add(line);
    }

    private void closeWriters() {
        try {
            if (intWriter != null) intWriter.close();
            if (floatWriter != null) floatWriter.close();
            if (stringWriter != null) stringWriter.close();
        } catch (IOException e) {
            System.err.println("Error when closing files for writing: " + e.getMessage());
        }
    }

    public Map<String, Statistics> getAllStatistics() {
        Map<String, Statistics> statsMap = new HashMap<>();
        statsMap.put("integers", intStatsCollector.getStatistics());
        statsMap.put("floats", floatStatsCollector.getStatistics());
        statsMap.put("strings", stringStatsCollector.getStatistics());
        return statsMap;
    }

    private Charset detectCharset(File file) throws IOException {
        try (InputStream stream = new BufferedInputStream(
                new FileInputStream(file))) {
            CharsetDetector detector = new CharsetDetector();
            detector.setText(stream);
            CharsetMatch match = detector.detect();
            return Charset.forName(match.getName());
        }
    }
}