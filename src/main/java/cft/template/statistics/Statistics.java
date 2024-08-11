package cft.template.statistics;

public class Statistics {
    private int count;
    private Double min;
    private Double max;
    private double sum;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    public void add(double value) {
        if (count == 0) {
            min = value;
            max = value;
        } else {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        sum += value;
        count++;
    }

    public void add(String value) {
        int length = value.length();
        if (length < minLength) minLength = length;
        if (length > maxLength) maxLength = length;
        count++;
    }

    public int getCount() {
        return count;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}