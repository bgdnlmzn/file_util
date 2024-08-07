package cft.template.cmd;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CmdArgs {
    @Parameter(names = "-o", description = "Путь для выходных файлов")
    private String outputPath = ".";

    @Parameter(names = "-p", description = "Префикс для выходных файлов")
    private String prefix = "";

    @Parameter(names = "-a", description = "Добавить в существующие файлы")
    private boolean append = false;

    @Parameter(names = "-s", description = "Показать краткую статистику")
    private boolean showShortStats = false;

    @Parameter(names = "-f", description = "Показать полную статистику")
    private boolean showFullStats = false;

    @Parameter(names = "-h", help = true, description = "Показать справку")
    private boolean help;

    @Parameter(description = "Файлы для обработки")
    private List<String> fileNames = new ArrayList<>();

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix(){
        return prefix;
    }

    public boolean isAppend() {
        return append;
    }

    public boolean isShowShortStats() {
        return showShortStats;
    }

    public boolean isShowFullStats() {
        return showFullStats;
    }

    public boolean isHelp() {
        return help;
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
