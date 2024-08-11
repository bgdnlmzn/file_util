package cft.template.cmd;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class CmdArgs {
    @Parameter(names = "-o", description = "Path for output files")
    private String outputPath = ".";

    @Parameter(names = "-p", description = "Prefix for output files")
    private String prefix = "";

    @Parameter(names = "-a", description = "Add to current files")
    private boolean append = false;

    @Parameter(names = "-s", description = "Show short statistics")
    private boolean showShortStats = false;

    @Parameter(names = "-f", description = "Show full statistics")
    private boolean showFullStats = false;

    @Parameter(names = "-h", help = true, description = "Show help")
    private boolean help;

    @Parameter(description = "Files for manage")
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