package com.apis.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by user on 9/4/16.
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PerformanceReport {
    @JsonProperty("Percentile10")
    private double percentile10;
    @JsonProperty("Percentile50")
    private double percentile50;
    @JsonProperty("Percentile90")
    private double percentile90;
    @JsonProperty("Percentile95")
    private double percentile95;
    @JsonProperty("Percentile99")
    private double percentile99;
    @JsonProperty("Mean")
    private double mean;
    @JsonProperty("StandardDeviation")
    private double standardDeviation;

    public double getPercentile10() {
        return percentile10;
    }

    public void setPercentile10(double percentile10) {
        this.percentile10 = percentile10;
    }

    public double getPercentile50() {
        return percentile50;
    }

    public void setPercentile50(double percentile50) {
        this.percentile50 = percentile50;
    }

    public double getPercentile90() {
        return percentile90;
    }

    public void setPercentile90(double percentile90) {
        this.percentile90 = percentile90;
    }

    public double getPercentile95() {
        return percentile95;
    }

    public void setPercentile95(double percentile95) {
        this.percentile95 = percentile95;
    }

    public double getPercentile99() {
        return percentile99;
    }

    public void setPercentile99(double percentile99) {
        this.percentile99 = percentile99;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

}
