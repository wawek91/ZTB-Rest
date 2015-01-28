package pl.edu.agh.ztb.service;
import org.codehaus.jackson.map.ObjectMapper;
import pl.edu.agh.ztb.mod2.dao.impl.*;
import pl.edu.agh.ztb.mod2.model.*;
import pl.edu.agh.ztb.mod2.model.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Marek on 2015-01-25.
 */
@Path("/objects")
public class ZtbMod2Service {

    @GET
    @Path("/cabinet/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCabinets() {
        CabinetDaoImpl  cabinetDao = new CabinetDaoImpl ();
        Set<Cabinet> cabinetDaoSet;
        try{
            cabinetDaoSet = cabinetDao.getAllCabinets();
            return Response.ok(cabinetDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(cabinet).build();
        }
        catch(Exception ex){
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
            return Response.ok(cabinet).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/cabinet/delete")
    public Response deleteCabinet(@FormParam("id") int id) {
        CabinetDaoImpl  cabinetDao = new CabinetDaoImpl ();
        try {
            cabinetDao.deleteCabinet(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Path("/cabinet/insert")
    public Response insertCabinet(@FormParam("cabinet") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        CabinetDaoImpl cabinetDao = new CabinetDaoImpl();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Cabinet cabinet = mapper.readValue(json, Cabinet.class);
            cabinetDao.insertCabinet(cabinet);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/cabinet/update")
    public Response updateCabinet(@FormParam("cabinet") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        CabinetDaoImpl cabinetDao = new CabinetDaoImpl();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Cabinet cabinet = mapper.readValue(json, Cabinet.class);
            cabinetDao.updateCabinet(cabinet);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
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
            return Response.ok(driverDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(driver).build();
        }
        catch(Exception ex){
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
            return Response.ok(driver).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/driver/delete")
    public Response deleteDriver(@FormParam("id") int id) {
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        try {
            driverDao.deleteDriver(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Path("/driver/insert")
    public Response insertDriver(@FormParam("driver") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Driver driver = mapper.readValue(json, Driver.class);
            driverDao.insertDriver(driver);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/driver/update")
    public Response updateDriver(@FormParam("driver") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        DriverDaoImpl  driverDao = new DriverDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Driver driver = mapper.readValue(json, Driver.class);
            driverDao.updateDriver(driver);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
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
            return Response.ok(fixtureDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(fixture).build();
        }
        catch(Exception ex){
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
            return Response.ok(fixture).build();
        }
        catch(Exception ex){
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
            return Response.ok(fixtureDaoSet).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/fixture/delete")
    public Response deleteFixture(@FormParam("id") int id) {
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        try {
            fixtureDao.deleteFixture(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Path("/fixture/insert")
    public Response insertFixture(@FormParam("fixture") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Fixture fixture = mapper.readValue(json, Fixture.class);
            fixtureDao.insertFixture(fixture);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/fixture/update")
    public Response updateFixture(@FormParam("fixture") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        FixturesDaoImpl  fixtureDao = new FixturesDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Fixture fixture = mapper.readValue(json, Fixture.class);
            fixtureDao.insertFixture(fixture);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
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
            return Response.ok(segmentDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(segment).build();
        }
        catch(Exception ex){
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
            return Response.ok(segmentDaoSet).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/segment/delete")
    public Response deleteSegment(@FormParam("id") int id) {
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        try {
            segmentDao.deleteSegmentController(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Path("/segment/insert")
    public Response insertSegment(@FormParam("segment") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            SegmentController segment = mapper.readValue(json, SegmentController.class);
            segmentDao.insertSegmentController(segment);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/segment/update")
    public Response updateSegment(@FormParam("segment") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SegmentControllersDaoImpl  segmentDao = new SegmentControllersDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            SegmentController segment = mapper.readValue(json, SegmentController.class);
            segmentDao.insertSegmentController(segment);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
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
            return Response.ok(sensorDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(sensor).build();
        }
        catch(Exception ex){
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
            return Response.ok(sensor).build();
        }
        catch(Exception ex){
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
            return Response.ok(sensorDaoSet).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/sensor/delete")
    public Response deleteSensor(@FormParam("id") int id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            sensorDao.deleteSensor(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }

    @POST
    @Path("/sensor/insert")
    public Response insertSensor(@FormParam("sensor") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Sensor sensor = mapper.readValue(json, Sensor.class);
            sensorDao.insertSensor(sensor);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/sensor/update")
    public Response updateSensor(@FormParam("sensor") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Sensor sensor = mapper.readValue(json, Sensor.class);
            sensorDao.updateSensor(sensor);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/sensor/update/data")
    public Response updateSensorData(@FormParam("id") int id, @FormParam("data") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Properties data = mapper.readValue(json, Properties.class);
            sensorDao.updateSensorData(id, data);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/sensor/select/data/{id}")
    public Response getSensorData(@PathParam("id") int id) {
        SensorDaoImpl  sensorDao = new SensorDaoImpl ();
        Properties data;
        try {
            data = sensorDao.getSensorData(id);
            return Response.ok(data).build();
        }
        catch(Exception ex){
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
            return Response.ok(errorDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(errorDaoSet).build();
        }
        catch(Exception ex){
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
            return Response.ok(errorDaoSet).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/errors/delete")
    public Response deleteAllErrors() {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.clearAllErrors();
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/errors/delete/fixture/{id}")
    public Response deleteFixtureErrors(@FormParam("id") int id) {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.clearFixtureErrors(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/errors/delete/driver/{id}")
    public Response deleteDriverErrors(@FormParam("id") int id) {
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            errorDao.clearDriverErrors(id);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/errors/insert/fixture")
    public Response insertFixtureError(@FormParam("error") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Error error = mapper.readValue(json, Error.class);
            errorDao.insertFixtureError(error);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Path("/errors/insert/driver")
    public Response insertDriverError(@FormParam("error") String json) {
        if(json == null || json.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ErrorDaoImpl  errorDao = new ErrorDaoImpl ();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Error error = mapper.readValue(json, Error.class);
            errorDao.insertDriverError(error);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }
}
