package com.apis;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class APIPerformanceConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty("url")
    String requestUrl;

    @JsonProperty("url")
    public String getRequestUrl() {
        return requestUrl;
    }

    @JsonProperty("url")
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
