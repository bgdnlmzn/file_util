package cft.template.files;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Класс для определения кодировки текстовых файлов.
 * Использует {@link CharsetDetector} и {@link CharsetMatch} библиотеки ICU4J.
 */
public class CharsetDetectorService {

    /**
     * Определяет кодировку указанного файла.
     *
     * @param file Файл, кодировку которого необходимо определить.
     * @return Кодировка файла.
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     */
    public static Charset detectCharset(File file) throws IOException {
        try (InputStream stream = new BufferedInputStream(
                new FileInputStream(file))) {
            CharsetDetector detector = new CharsetDetector();
            detector.setText(stream);
            CharsetMatch match = detector.detect();
            return Charset.forName(match.getName());
        }
    }
}