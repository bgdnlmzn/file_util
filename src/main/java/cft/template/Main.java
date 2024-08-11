package cft.template;

import com.beust.jcommander.JCommander;

import cft.template.command.CmdArgs;
import cft.template.files.FileManager;
import cft.template.statistics.StatisticsPrinter;

public class Main {
    public static void main(String[] args) {
        CmdArgs cmdArgs = new CmdArgs();
        JCommander.newBuilder()
                .addObject(cmdArgs)
                .build()
                .parse(args);

        if (cmdArgs.isHelp()) {
            JCommander.newBuilder()
                    .addObject(cmdArgs)
                    .build()
                    .usage();
            return;
        }

        FileManager fileManager = new FileManager(cmdArgs.getOutputPath(),
                cmdArgs.getPrefix(),
                cmdArgs.isAppend());

        fileManager.manageFiles(cmdArgs.getFileNames());

        StatisticsPrinter.printStatistics(fileManager.getAllStatistics(),
                cmdArgs.isShowShortStats(),
                cmdArgs.isShowFullStats());
    }
}