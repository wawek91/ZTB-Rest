package pl.edu.agh.ztb.service;

import java.util.Properties;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import loggers.enums.SourceType;
import loggers.impl.RestLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.edu.agh.ztb.mod2.dao.SensorDao;
import pl.edu.agh.ztb.mod2.dao.impl.SensorDaoImpl;
import pl.edu.agh.ztb.mod2.model.Sensor;

@Path("/sensors_types")
public class SensorsTypesService {

    private RestLogger logger;
    
    public SensorsTypesService() {
    	ApplicationContext springContext = new ClassPathXmlApplicationContext("ztb7-context.xml");
		logger = springContext.getBean(RestLogger.class);
    }

    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensors() {
        SensorDao sensorDao= new SensorDaoImpl();
        Set<Sensor> sensors;
        try{
            sensors = sensorDao.getAllSensors();
            logger.logSuccess(SourceType.MANUAL, "All Sensors fetched");
            return Response.ok(sensors).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorById(@PathParam("id") Integer id) {
        SensorDao sensorDao= new SensorDaoImpl();
        try{
            Sensor sensor = sensorDao.getSensor(id);
            if(sensor == null){
                logger.logFailure(SourceType.MANUAL, "Sensor " + id + " not found");
                return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            }
            logger.logSuccess(SourceType.MANUAL, sensor.toString());
            return Response.ok(sensor).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insertSensor(SensorDto sensor) {
        if(sensor == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDao sensorDao= new SensorDaoImpl();
        try {
            sensorDao.insertSensor(sensor);
            logger.logSuccess(SourceType.MANUAL, "Sensor " + sensor.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateSensor(SensorDto sensor) {
        if(sensor == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDao sensorDao= new SensorDaoImpl();
        try {
            sensorDao.updateSensor(sensor);
            logger.logSuccess(SourceType.MANUAL, "Sensor " + sensor.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response deleteSensor(Integer id) {
        if(id == null) {
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDao sensorDao= new SensorDaoImpl();
        try {
            sensorDao.deleteSensor(id);
            logger.logSuccess(SourceType.MANUAL, "Sensor " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }
}
