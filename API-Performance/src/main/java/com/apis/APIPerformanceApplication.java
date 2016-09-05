package com.apis;

import com.apis.resources.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class APIPerformanceApplication extends Application<APIPerformanceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new APIPerformanceApplication().run(args);
    }

    @Override
    public String getName() {
        return "APIPerformance";
    }

    @Override
    public void initialize(final Bootstrap<APIPerformanceConfiguration> bootstrap) {
    }

    @Override
    public void run(final APIPerformanceConfiguration configuration,
                    final Environment environment) {

        MessageResource resource = new MessageResource();
        environment.jersey().register(resource);
    }

}
