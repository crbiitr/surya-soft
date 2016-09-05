package com.apis.api;

import com.apis.core.WorkerThread;
import com.apis.pojo.GetStats;
import com.apis.pojo.PerformanceReport;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Chetan on 9/4/16.
 */
public class ServiceAPI {
    PerformanceReport getReport = new PerformanceReport();
    PerformanceReport postReport = new PerformanceReport();
    GetStats stats = new GetStats();

    public Response call() {
        ExecutorService executor = Executors.newFixedThreadPool(10);//creating a pool of 5 threads
        List<Integer> getMethodResponseTime = new LinkedList<Integer>();
        List<Integer> postMethodResponseTime = new LinkedList<Integer>();
        for (int i = 0; i < 100; i++) {
            Runnable worker = new WorkerThread(getMethodResponseTime, postMethodResponseTime);
            executor.execute(worker);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("postMethodResponseTime = " + postMethodResponseTime);
        System.out.println("getMethodResponseTime = " + getMethodResponseTime);
        System.out.println("Finished all threads");

        getPercentileMeanStd(getMethodResponseTime,getReport);
        getPercentileMeanStd(postMethodResponseTime,postReport);
        stats.setGetPerformanceReport(getReport);
        stats.setPostPerformanceReport(postReport);

        return null;
    }

    public void getPercentileMeanStd(List<Integer> getMethodResponseTime, PerformanceReport reportResponse) {
        // Get a DescriptiveStatistics instance
        DescriptiveStatistics stats = new DescriptiveStatistics();

        // Add the data from the array
        for( int i = 0; i < getMethodResponseTime.size(); i++) {
            stats.addValue(getMethodResponseTime.get(i));
        }

        // Computing statistics
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();
        double percentile10 = stats.getPercentile(10);
        double percentile50 = stats.getPercentile(50);
        double percentile90 = stats.getPercentile(90);
        double percentile95 = stats.getPercentile(95);
        double percentile99 = stats.getPercentile(99);

        reportResponse.setMean(mean);
        reportResponse.setStandardDeviation(std);
        reportResponse.setPercentile10(percentile10);
        reportResponse.setPercentile50(percentile50);
        reportResponse.setPercentile90(percentile90);
        reportResponse.setPercentile95(percentile95);
        reportResponse.setPercentile99(percentile99);

    }

}
