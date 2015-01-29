package pl.edu.agh.ztb.service;

import loggers.enums.SourceType;
import loggers.impl.RestLogger;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.ztb.mod2.dao.impl.*;
import pl.edu.agh.ztb.mod2.model.*;
import pl.edu.agh.ztb.mod2.model.Error;
import pl.edu.agh.ztb.service.managers.LoggerManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Marek on 2015-01-25.
 */
@Path("/objects")
public class ZtbMod2Service {

    private RestLogger logger;

    public ZtbMod2Service() {
        ApplicationContext springContext = new ClassPathXmlApplicationContext("ztb7-context.xml");
        logger = LoggerManager.getLoggerInstance();
    }

    @GET
    @Path("/cabinet/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCabinets() {
        CabinetDaoImpl  cabinetDao = new CabinetDaoImpl ();
        Set<Cabinet> cabinetDaoSet;
        try{
            cabinetDaoSet = cabinetDao.getAllCabinets();
            logger.logSuccess(SourceType.MANUAL, "All Cabinets fetched");
            return Response.ok(cabinetDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/cabinet/select/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCabinet(@PathParam("id") int id) {
        CabinetDaoImpl  cabinetDao = new CabinetDaoImpl ();
        Cabinet cabinet;
        try{
            cabinet = cabinetDao.getCabinet(id);
            logger.logSuccess(SourceType.MANUAL, cabinet.toString());
            return Response.ok(cabinet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/cabinet/select/location/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCabinetByLocation(@PathParam("id") int id) {
        CabinetDaoImpl  cabinetDao = new CabinetDaoImpl ();
        Cabinet cabinet;
        try{
            cabinet = cabinetDao.getCabinetByLocation(id);
            logger.logSuccess(SourceType.MANUAL, cabinet.toString());
            return Response.ok(cabinet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cabinet/delete")
    public Response deleteCabinet(Integer id) {
        CabinetDaoImpl  cabinetDao = new CabinetDaoImpl ();
        try {
            cabinetDao.deleteCabinet(id);
            logger.logSuccess(SourceType.MANUAL, "Cabinet " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cabinet/insert")
    public Response insertCabinet(CabinetTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        CabinetDaoImpl cabinetDao = new CabinetDaoImpl();
        try {
            cabinetDao.insertCabinet(json);
            logger.logSuccess(SourceType.MANUAL, "Cabinet " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown " + ex.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cabinet/update")
    public Response updateCabinet(CabinetTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        CabinetDaoImpl cabinetDao = new CabinetDaoImpl();
        try {
            cabinetDao.updateCabinet(json);
            logger.logSuccess(SourceType.MANUAL, "Cabinet " + json.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @GET
    @Path("/driver/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDrivers() {
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        Set<Driver> driverDaoSet;
        try{
            driverDaoSet = driverDao.getAllDrivers();
            logger.logSuccess(SourceType.MANUAL, "All Drivers fetched");
            return Response.ok(driverDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/driver/select/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDriver(@PathParam("id") int id) {
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        Driver driver;
        try{
            driver = driverDao.getDriver(id);
            logger.logSuccess(SourceType.MANUAL, driver.toString());
            return Response.ok(driver).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/driver/select/fixture/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDriverByFixture(@PathParam("id") int id) {
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        Driver driver;
        try{
            driver = driverDao.getDriverByFixture(id);
            logger.logSuccess(SourceType.MANUAL, driver.toString());
            return Response.ok(driver).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/driver/delete")
    public Response deleteDriver(Integer id) {
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        try {
            driverDao.deleteDriver(id);
            logger.logSuccess(SourceType.MANUAL, "Driver " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/driver/insert")
    public Response insertDriver(DriverTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        try {
            driverDao.insertDriver(json);
            logger.logSuccess(SourceType.MANUAL, "Driver " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/driver/update")
    public Response updateDriver(DriverTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        try {
            driverDao.updateDriver(json);
            logger.logSuccess(SourceType.MANUAL, "Driver " + json.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @GET
    @Path("/fixture/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFixtures() {
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        Set<Fixture> fixtureDaoSet;
        try{
            fixtureDaoSet = fixtureDao.getAllFixtures();
            logger.logSuccess(SourceType.MANUAL, "All Fixtures fetched");
            return Response.ok(fixtureDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/fixture/select/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixture(@PathParam("id") int id) {
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        Fixture fixture;
        try{
            fixture = fixtureDao.getFixture(id);
            logger.logSuccess(SourceType.MANUAL, fixture.toString());
            return Response.ok(fixture).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/fixture/select/location/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixtureByLocation(@PathParam("id") int id) {
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        Fixture fixture;
        try{
            fixture = fixtureDao.getFixtureByLocation(id);
            logger.logSuccess(SourceType.MANUAL, fixture.toString());
            return Response.ok(fixture).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/fixture/select/segment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixtureBySegment(@PathParam("id") int id) {
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        Set<Fixture> fixtureDaoSet;
        try{
            fixtureDaoSet = fixtureDao.getFixturesBySegmentCtrl(id);
            logger.logSuccess(SourceType.MANUAL, "All Fixtures with segmentId " + id + " fetched");
            return Response.ok(fixtureDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/fixture/delete")
    public Response deleteFixture(Integer id) {
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        try {
            fixtureDao.deleteFixture(id);
            logger.logSuccess(SourceType.MANUAL, "Fixture " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/fixture/insert")
    public Response insertFixture(FixtureTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        try {
            fixtureDao.insertFixture(json);
            logger.logSuccess(SourceType.MANUAL, "Fixture " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/fixture/update")
    public Response updateFixture(FixtureTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        try {
            fixtureDao.updateFixture(json);
            logger.logSuccess(SourceType.MANUAL, "Fixture " + json.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @GET
    @Path("/segment/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSegments() {
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        Set<SegmentController> segmentDaoSet;
        try{
            segmentDaoSet = segmentDao.getAllSegmentControllers();
            logger.logSuccess(SourceType.MANUAL, "All Segments fetched");
            return Response.ok(segmentDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/segment/select/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSegment(@PathParam("id") int id) {
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        SegmentController segment;
        try{
            segment = segmentDao.getSegmentController(id);
            logger.logSuccess(SourceType.MANUAL, segment.toString());
            return Response.ok(segment).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/segment/select/cabinet/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSegmentByCabinet(@PathParam("id") int id) {
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        Set<SegmentController> segmentDaoSet;
        try{
            segmentDaoSet = segmentDao.getSegmentControllerByCabinet(id);
            logger.logSuccess(SourceType.MANUAL, "All Segments with cabinetId " + id + " fetched");
            return Response.ok(segmentDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/segment/delete")
    public Response deleteSegment(Integer id) {
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        try {
            segmentDao.deleteSegmentController(id);
            logger.logSuccess(SourceType.MANUAL, "Segment " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/segment/insert")
    public Response insertSegment(SegmentControllerTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        try {
            segmentDao.insertSegmentController(json);
            logger.logSuccess(SourceType.MANUAL, "Segment " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/segment/update")
    public Response updateSegment(SegmentControllerTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        try {
            segmentDao.updateSegmentController(json);
            logger.logSuccess(SourceType.MANUAL, "Segment " + json.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @GET
    @Path("/sensor/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors() {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        Set<Sensor> sensorDaoSet;
        try{
            sensorDaoSet = sensorDao.getAllSensors();
            logger.logSuccess(SourceType.MANUAL, "All Sensors fetched");
            return Response.ok(sensorDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/sensor/select/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensor(@PathParam("id") int id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        Sensor sensor;
        try{
            sensor = sensorDao.getSensor(id);
            logger.logSuccess(SourceType.MANUAL, sensor.toString());
            return Response.ok(sensor).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/sensor/select/location/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorByLocation(@PathParam("id") int id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        Sensor sensor;
        try{
            sensor = sensorDao.getSensorByLocation(id);
            logger.logSuccess(SourceType.MANUAL, sensor.toString());
            return Response.ok(sensor).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/sensor/select/segment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorsBySegment(@PathParam("id") int id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        Set<Sensor> sensorDaoSet;
        try{
            sensorDaoSet = sensorDao.getSensorsBySegmentCtrl(id);
            logger.logSuccess(SourceType.MANUAL, "All Sensors with segmentId " + id + " fetched");
            return Response.ok(sensorDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sensor/delete")
    public Response deleteSensor(Integer id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sensor/insert")
    public Response insertSensor(SensorTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            sensorDao.insertSensor(json);
            logger.logSuccess(SourceType.MANUAL, "Sensor " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sensor/update")
    public Response updateSensor(SensorTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            sensorDao.updateSensor(json);
            logger.logSuccess(SourceType.MANUAL, "Sensor " + json.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sensor/update/data")
    public Response updateSensorData(SensorTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            sensorDao.updateSensorData(json.getId(), json.getSensor_data());
            logger.logSuccess(SourceType.MANUAL, "Data of Sensor with id " + json.getId() + " updated");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/sensor/select/data/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorData(@PathParam("id") int id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        Properties data;
        try {
            data = sensorDao.getSensorData(id);
            logger.logSuccess(SourceType.MANUAL, data.toString());
            return Response.ok(data).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/errors/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllErrors() {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        Set<Error> errorDaoSet;
        try{
            errorDaoSet = errorDao.getAllErrors();
            logger.logSuccess(SourceType.MANUAL, "All Errors fetched");
            return Response.ok(errorDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/errors/select/fixture/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixtureErrors(@PathParam("id") int id) {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        Set<Error> errorDaoSet;
        try{
            errorDaoSet = errorDao.getFixtureErrors(id);
            logger.logSuccess(SourceType.MANUAL, "All FixtureErrors with id: " + id + " fetched");
            return Response.ok(errorDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/errors/select/driver/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDriverErrors(@PathParam("id") int id) {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        Set<Error> errorDaoSet;
        try{
            errorDaoSet = errorDao.getDriverErrors(id);
            logger.logSuccess(SourceType.MANUAL, "All DriverErrors with id: " + id + " fetched");
            return Response.ok(errorDaoSet).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/errors/delete")
    public Response deleteAllErrors() {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.clearAllErrors();
            logger.logSuccess(SourceType.MANUAL, "All Errors deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/errors/delete/fixture")
    public Response deleteFixtureErrors(Integer id) {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.clearFixtureErrors(id);
            logger.logSuccess(SourceType.MANUAL, "FixtureError " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/errors/delete/driver")
    public Response deleteDriverErrors(Integer id) {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.clearDriverErrors(id);
            logger.logSuccess(SourceType.MANUAL, "DriverError " + id + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/errors/insert/fixture")
    public Response insertFixtureError(ErrorTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.insertFixtureError(json);
            logger.logSuccess(SourceType.MANUAL, "FixtureError " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/errors/insert/driver")
    public Response insertDriverError(ErrorTmp json) {
        if(json == null){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.insertDriverError(json);
            logger.logSuccess(SourceType.MANUAL, "DriverError " + json.getId() + " inserted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }
}
