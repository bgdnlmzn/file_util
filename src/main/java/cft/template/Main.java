package cft.template;
import com.beust.jcommander.JCommander;
import cft.template.cmd.CmdArgs;
import cft.template.manager.FileManager;
import cft.template.statistics.StatisticsPrinter;

public class Main {
    public static void main(String[] args) {
        CmdArgs cmdArgs = new CmdArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(cmdArgs)
                .build();
        jCommander.parse(args);

        if (cmdArgs.isHelp()) {
            jCommander.usage();
            return;
        }

        FileManager manager = new FileManager(cmdArgs.getOutputPath(),
                cmdArgs.getPrefix(),
                cmdArgs.isAppend());
        manager.manageFiles(cmdArgs.getFileNames());

        StatisticsPrinter.printStatistics(manager.getAllStatistics(),
                cmdArgs.isShowFullStats());
    }
}