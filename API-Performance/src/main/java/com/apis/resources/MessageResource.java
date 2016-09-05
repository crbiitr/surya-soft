package com.apis.resources;

import com.apis.api.ServiceAPI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Chetan on 9/4/16.
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @GET
    @Path("/performance-test")
    public Response performanceTest() {
        ServiceAPI service = new ServiceAPI();
        Response response = service.call();
        return response;
    }
}
