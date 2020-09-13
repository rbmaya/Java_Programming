package Parser;

import java.nio.file.Path;

public class Controller {
    private final Path inpPath;
    private final Path outPath;

    public Controller(Path inpPath, Path outPath){
        this.inpPath = inpPath;
        this.outPath = outPath;
    }

    public void process() throws Exception {
        Reader reader = new Reader(inpPath);

        WordStatistic statistic = reader.reading();

        statistic.sortByValue();

        Reporter reporter = new Reporter();

        reporter.getReport(statistic, outPath);
    }
}
