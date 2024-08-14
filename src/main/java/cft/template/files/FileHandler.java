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

/**
 * Класс для обработки файлов: чтение и запись данных. Используется в {@link FileManager} и {@link FileWriter}.
 */
public class FileHandler {
    private final String outputPath;
    private final String prefix;
    private final boolean append;

    /**
     * Конструктор для класса.
     * Создает экземпляр {@code FileHandler} с заданными параметрами.
     *
     * @param outputPath Путь к директории для сохранения выходных файлов.
     * @param prefix Префикс для имен выходных файлов.
     * @param append Флаг, указывающий на необходимость добавления содержимого в существующий файл.
     */
    public FileHandler(String outputPath, String prefix, boolean append) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.append = append;

        createDirIfNotExists();
    }

    /**
     * Возвращает {@code BufferedReader} для чтения файла с указанной кодировкой.
     *
     * @param file Файл для чтения.
     * @param charset Кодировка файла.
     * @return {@code BufferedReader} для чтения файла.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    public BufferedReader getReader(File file, Charset charset) throws IOException {
        return Files.newBufferedReader(file.toPath(), charset);
    }

    /**
     * Создает и возвращает {@code BufferedWriter} для записи данных в файл.
     *
     * @param fileName Имя файла для записи.
     * @return {@code BufferedWriter} для записи данных.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    public BufferedWriter createWriter(String fileName) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(getOutputFile(fileName), append),
                        StandardCharsets.UTF_8
                )
        );
    }

    /**
     * Возвращает файл с полным путем для записи данных.
     *
     * @param fileName Имя выходного файла.
     * @return Файл для записи.
     */
    private File getOutputFile(String fileName) {
        return new File(outputPath
                + File.separator
                + prefix
                + fileName);
    }

    /**
     * Создает директорию, если она не существует.
     */
    private void createDirIfNotExists() {
        File dir = new File(outputPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}