package cft.template.command;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CmdArgs {
    @Parameter(names = "-o")
    private String outputPath = System.getProperty("user.dir");

    @Parameter(names = "-p")
    private String prefix = "";

    @Parameter(names = "-a")
    private boolean append = false;

    @Parameter(names = "-s")
    private boolean showShortStats = false;

    @Parameter(names = "-f")
    private boolean showFullStats = false;

    @Parameter
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

    public List<String> getFileNames() {
        return fileNames;
    }
}