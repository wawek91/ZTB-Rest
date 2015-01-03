package pl.edu.agh.ztb.service;

import pl.edu.agh.ztb.model.FakeClass;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by wawek on 03.01.15.
 */

@Path("/service")
public class FakeService {

    @GET
    @Path("/fake_service/{fake}")
    @Produces(MediaType.APPLICATION_JSON)
    public FakeClass fakeMethod(@PathParam("fake") String fake) {
        FakeClass fakeClass = new FakeClass(1, fake);
        return fakeClass;
    }
}
