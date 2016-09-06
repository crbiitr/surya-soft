package com.apis;

import com.apis.resources.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class APIPerformanceApplication extends Application<APIPerformanceConfiguration>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(APIPerformanceApplication.class);

    public static void main(String[] args) throws Exception {
        new APIPerformanceApplication().run(args);
    }

    @Override
    public String getName() {
        return "APIPerformance";
    }

    @Override
    public void initialize(Bootstrap<APIPerformanceConfiguration> bootstrap) {
    }

    @Override
    public void run(APIPerformanceConfiguration configuration,
                    Environment environment) {
        LOGGER.info("Main application is running");
        MessageResource resource = new MessageResource(configuration.getRequestUrl());
        environment.jersey().register(resource);
    }

}