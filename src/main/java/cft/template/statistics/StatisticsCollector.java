package cft.template.statistics;

public class StatisticsCollector {
    private final Statistics statistics = new Statistics();

    public void add(String value) {
        try {
            double numericValue = Double.parseDouble(value);
            statistics.add(numericValue);
        } catch (NumberFormatException e) {
            statistics.add(value);
        }
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
