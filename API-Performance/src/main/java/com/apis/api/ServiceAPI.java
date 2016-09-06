package com.apis.api;

import com.apis.core.WorkerThread;
import com.apis.pojo.GetStats;
import com.apis.pojo.PerformanceReport;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Chetan
 */
public class ServiceAPI {
    private static Logger logger = LoggerFactory.getLogger(ServiceAPI.class);
    PerformanceReport getReport = new PerformanceReport();
    PerformanceReport postReport = new PerformanceReport();
    GetStats stats = new GetStats();

    public Response call(String url) {
        ExecutorService executor = Executors.newFixedThreadPool(10);//creating a pool of 5 threads
        List<Integer> getMethodResponseTime = new LinkedList<Integer>();
        List<Integer> postMethodResponseTime = new LinkedList<Integer>();
        for (int i = 0; i < 100; i++) {
            Runnable worker = new WorkerThread(getMethodResponseTime, postMethodResponseTime, url);
            executor.execute(worker);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        logger.info("postMethodResponseTime = " + postMethodResponseTime);
        logger.info("getMethodResponseTime = " + getMethodResponseTime);
        logger.info("Finished all threads");

        getPercentileMeanStd(getMethodResponseTime,getReport);
        getPercentileMeanStd(postMethodResponseTime,postReport);
        stats.setGetPerformanceReport(getReport);
        stats.setPostPerformanceReport(postReport);
        return Response.status(200).entity(stats).build();
    }

    public void getPercentileMeanStd(List<Integer> getMethodResponseTime, PerformanceReport reportResponse) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        // Add the data from the List
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
