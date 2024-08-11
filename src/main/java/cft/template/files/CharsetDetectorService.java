package cft.template.files;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class CharsetDetectorService {

    public Charset detectCharset(File file) throws IOException {
        try (InputStream stream = new BufferedInputStream(
                new FileInputStream(file))) {
            CharsetDetector detector = new CharsetDetector();
            detector.setText(stream);
            CharsetMatch match = detector.detect();
            return Charset.forName(match.getName());
        }
    }
}