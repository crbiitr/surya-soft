package com.apis.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.apis.pojo.Message;

/**
 * Created by Chetan on 9/4/16.
 */
public class WorkerThread implements Runnable {
    List<Integer> getMethodResponseTime;
    List<Integer> postMethodResponseTime;

    public WorkerThread(List<Integer> getMethodResponseTime, List<Integer> postMethodResponseTime) {
        this.getMethodResponseTime = getMethodResponseTime;
        this.postMethodResponseTime = postMethodResponseTime;
    }

    @Override
    public void run() {
        try {
            Message message = getMessage();
            System.out.println("message = " + message);
            int resultStatusCode = postMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int postMessage(Message message) throws Exception {
        String url = "http://surya-interview.appspot.com/message";

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);


        StringEntity postingString = new StringEntity(new ObjectMapper().writeValueAsString(message));
        post.setEntity(postingString);

        post.setHeader("Content-type", "application/json");
        long startTime = System.currentTimeMillis();
        HttpResponse response = client.execute(post);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Total elapsed POST http request/response time in milliseconds: " + elapsedTime);

        int responseCode = response.getStatusLine().getStatusCode();
        postMethodResponseTime.add((int) elapsedTime);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        return 0;
    }

    private Message getMessage() throws Exception {
        String url = "http://surya-interview.appspot.com/message";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        request.addHeader("X-Surya-Email-Id", "gps@surya-soft.com");
        long startTime = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Total elapsed GET http request/response time in milliseconds: " + elapsedTime);
        getMethodResponseTime.add((int) elapsedTime);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String responseString = result.toString();
        Message message = objectMapper.readValue(responseString, Message.class);
        return message;
    }
}
