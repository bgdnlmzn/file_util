package cft.template.files;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileHandler {
    private final String outputPath;
    private final String prefix;
    private final boolean append;

    public FileHandler(String outputPath, String prefix, boolean append) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.append = append;

        createDirIfNotExists();
    }

    public BufferedReader getReader(File file, Charset charset) throws IOException {
        return Files.newBufferedReader(file.toPath(), charset);
    }

    public BufferedWriter createWriter(String fileName) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(getOutputFile(fileName), append),
                        StandardCharsets.UTF_8
                )
        );
    }

    private File getOutputFile(String fileName) {
        return new File(outputPath
                + File.separator
                + prefix
                + fileName);
    }

    private void createDirIfNotExists() {
        File dir = new File(outputPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}