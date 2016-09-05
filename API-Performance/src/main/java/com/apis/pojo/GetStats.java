package com.apis.pojo;

/**
 * Created by user on 9/4/16.
 */
public class GetStats {
    private PerformanceReport getPerformanceReport;
    private PerformanceReport postPerformanceReport;

    public PerformanceReport getPostPerformanceReport() {
        return postPerformanceReport;
    }

    public void setPostPerformanceReport(PerformanceReport postPerformanceReport) {
        this.postPerformanceReport = postPerformanceReport;
    }

    public PerformanceReport getGetPerformanceReport() {
        return getPerformanceReport;
    }

    public void setGetPerformanceReport(PerformanceReport getPerformanceReport) {
        this.getPerformanceReport = getPerformanceReport;
    }

}
