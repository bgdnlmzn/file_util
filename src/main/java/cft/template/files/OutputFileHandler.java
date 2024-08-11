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

public class OutputFileHandler {
    private final String outputPath;
    private final String prefix;
    private final boolean append;

    private BufferedWriter intWriter;
    private BufferedWriter floatWriter;
    private BufferedWriter stringWriter;

    //private boolean intDataWritten = false;
    //private boolean floatDataWritten = false;
    //private boolean stringDataWritten = false;

    public OutputFileHandler(String outputPath, String prefix, boolean append) {
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

    public BufferedReader getReader(File file, Charset charset) throws IOException {
        return Files.newBufferedReader(file.toPath(), charset);
    }

    public BufferedWriter getIntWriter() throws IOException {
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

    public BufferedWriter getFloatWriter() throws IOException {
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

    public BufferedWriter getStringWriter() throws IOException {
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

    public void closeWriters() {
        try {
            if (intWriter != null) intWriter.close();
            if (floatWriter != null) floatWriter.close();
            if (stringWriter != null) stringWriter.close();
        } catch (IOException e) {
            System.err.println("Error when closing files for writing: " + e.getMessage());
        }
    }
}
