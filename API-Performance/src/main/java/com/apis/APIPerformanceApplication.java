package com.apis;

import com.apis.resources.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class APIPerformanceApplication extends Application<APIPerformanceConfiguration> {

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

        MessageResource resource = new MessageResource();
        environment.jersey().register(resource);
    }

}