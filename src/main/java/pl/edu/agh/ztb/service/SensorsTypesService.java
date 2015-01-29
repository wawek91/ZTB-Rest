package pl.edu.agh.ztb.service;

import database.DBAccess;
import database.SensorDataFormat;
import database.SensorType;
import database.WorkingArea;
import loggers.enums.SourceType;
import loggers.impl.RestLogger;
import pl.edu.agh.ztb.service.managers.LoggerManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sensors_types")
public class SensorsTypesService {

    private RestLogger logger;
    
    public SensorsTypesService() {
    	logger = LoggerManager.getLoggerInstance();
    }

    @GET
    @Path("/select/sensor_type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorTypes() {
        DBAccess db = new DBAccess();
        List<SensorType> sensors;
        try{
            sensors = db.getSensorsTypes();
            logger.logSuccess(SourceType.MANUAL, "All Sensor Types fetched");
            return Response.ok(sensors).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/select/sensor_data_format")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorDataFormats() {
        DBAccess db = new DBAccess();
        List<SensorDataFormat> sensors;
        try{
            sensors = db.getSensorsDataFormats();
            logger.logSuccess(SourceType.MANUAL, "All Sensor Data Formats fetched");
            return Response.ok(sensors).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/select/sensor_type/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorTypeById(@PathParam("id") Integer id) {
        DBAccess db = new DBAccess();
        try{
            SensorType sensor = db.getSensorType(id);
            if(sensor == null){
                logger.logFailure(SourceType.MANUAL, "Sensor Type " + id + " not found");
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

    @GET
    @Path("/select/sensor_data_format/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorDataFormatById(@PathParam("id") Integer id) {
        DBAccess db = new DBAccess();
        try{
            SensorDataFormat dataFormat = db.getDataFormat(id);
            if(dataFormat == null){
                logger.logFailure(SourceType.MANUAL, "Sensor Data Format " + id + " not found");
                return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            }
            logger.logSuccess(SourceType.MANUAL, dataFormat.toString());
            return Response.ok(dataFormat).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/select/working_area/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkingAreas(@PathParam("id") Integer id) {
        DBAccess db = new DBAccess();
        try{
            List<WorkingArea> workingAreas = db.getWorkingArea(id);
            if(workingAreas == null){
                logger.logFailure(SourceType.MANUAL, "Working Area " + id + " not found");
                return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            }
            logger.logSuccess(SourceType.MANUAL, workingAreas.toString());
            return Response.ok(workingAreas).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/select/sensors_data_format/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorsDataFormatById(@PathParam("id") Integer id) {
        DBAccess db = new DBAccess();
        try{
            List<SensorDataFormat> sensors = db.getSensorDataFormat(id);
            if(sensors == null){
                logger.logFailure(SourceType.MANUAL, "Sensors Data Format " + id + " not found");
                return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            }
            logger.logSuccess(SourceType.MANUAL, sensors.toString());
            return Response.ok(sensors).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert/sensor_data_format")
    public Response insertSensorDataFormat(SensorDataFormat sensor) {
        if(sensor == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DBAccess db = new DBAccess();
        try {
            db.addSensorDataFormat(sensor);
            logger.logSuccess(SourceType.MANUAL, "Sensor Data Format " + sensor.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert/sensor_type")
    public Response insertSensorType(SensorType sensor) {
        if(sensor == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DBAccess db = new DBAccess();
        try {
            db.saveSensorType(sensor);
            logger.logSuccess(SourceType.MANUAL, "Sensor Type " + sensor.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/sensor_data_format")
    public Response deleteSensorDataFormat(SensorDataFormat sdf) {
        if(sdf == null) {
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DBAccess db = new DBAccess();
        try {
            db.deleteDataFormat(sdf);
            logger.logSuccess(SourceType.MANUAL, "Sensor Data Format " + sdf.getId() + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/sensor_type")
    public Response deleteSensorDataFormat(SensorType st) {
        if(st == null) {
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DBAccess db = new DBAccess();
        try {
            db.deleteSensorType(st);
            logger.logSuccess(SourceType.MANUAL, "Sensor Type " + st.getId() + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }
}
