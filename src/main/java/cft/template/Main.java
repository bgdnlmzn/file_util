package cft.template;

import com.beust.jcommander.JCommander;

import cft.template.command.CmdArgs;
import cft.template.files.FileManager;
import cft.template.statistics.StatisticsPrinter;

/**
 * Главный класс приложения.
 * Обрабатывает аргументы командной строки с помощью {@link CmdArgs}.
 * Управляет обработкой файлов с использованием {@link FileManager}.
 * Выводит статистику с помощью {@link StatisticsPrinter}.
 */
public class Main {
    /**
     * @param args Аргументы командной строки, переданные приложению.
     *             Они парсятся и обрабатываются с помощью {@link JCommander}.
     */
    public static void main(String[] args) {
        CmdArgs cmdArgs = new CmdArgs();
        JCommander.newBuilder()
                .addObject(cmdArgs)
                .build()
                .parse(args);

        FileManager fileManager = new FileManager(cmdArgs.getOutputPath(),
                cmdArgs.getPrefix(),
                cmdArgs.isAppend());

        fileManager.manageFiles(cmdArgs.getFileNames());

        StatisticsPrinter.printStatistics(fileManager.getAllStatistics(),
                cmdArgs.isShowShortStats(),
                cmdArgs.isShowFullStats());
    }
}