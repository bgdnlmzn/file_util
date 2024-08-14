package cft.template.command;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки аргументов командной строки.
 * Использует библиотеку JCommander, аннотацию {@link Parameter}, для парсинга аргументов.
 */
public class CmdArgs {
    /**
     * Путь к выходным файлам. По умолчанию используется текущая директория.
     */
    @Parameter(names = "-o")
    private String outputPath = System.getProperty("user.dir");

    /**
     * Префикс для имен выходных файлов.
     */
    @Parameter(names = "-p")
    private String prefix = "";

    /**
     * Опция добавления содержимого в существующий файл.
     */
    @Parameter(names = "-a")
    private boolean append = false;

    /**
     * Опция отображения краткой статистики.
     */
    @Parameter(names = "-s")
    private boolean showShortStats = false;

    /**
     * Опция отображения полной статистики.
     */
    @Parameter(names = "-f")
    private boolean showFullStats = false;

    /**
     * Список имен файлов для обработки.
     */
    @Parameter
    private List<String> fileNames = new ArrayList<>();

    /**
     * Возвращает путь к выходному файлу.
     *
     * @return Путь к выходному файлу.
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * Возвращает префикс для имен выходных файлов.
     *
     * @return Префикс для имен выходных файлов.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Проверяет, установлена ли опция добавления содержимого в существующий файл.
     *
     * @return {@code true}, если установлена опция добавления, иначе {@code false}.
     */
    public boolean isAppend() {
        return append;
    }

    /**
     * Проверяет, включена ли опция отображения краткой статистики.
     *
     * @return {@code true}, если включено отображение краткой статистики, иначе {@code false}.
     */
    public boolean isShowShortStats() {
        return showShortStats;
    }

    /**
     * Проверяет, включена ли опция отображения полной статистики.
     *
     * @return {@code true}, если включено отображение полной статистики, иначе {@code false}.
     */
    public boolean isShowFullStats() {
        return showFullStats;
    }

    /**
     * Возвращает список имен файлов для обработки.
     *
     * @return Список имен файлов.
     */
    public List<String> getFileNames() {
        return fileNames;
    }
}