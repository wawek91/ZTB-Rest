package pl.edu.agh.ztb.service;

import agh.db.ControlRuleDaoImpl;
import loggers.enums.SourceType;
import loggers.impl.RestLogger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/control_rules")
public class ControlRulesService {

    private static final RestLogger logger = new RestLogger();
    
    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getControlRules() {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        List<String> controlRuleList;
        try{
            controlRuleList = controlRuleDao.getAllControlRules();
            logger.logSuccess(SourceType.MANUAL, "All Control Rules fetched");
            return Response.ok(controlRuleList).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @GET
    @Path("/select/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getControlRuleByName(@PathParam("name") String name) {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        try{
            String controlRule = controlRuleDao.getControlRule(name);
            if(controlRule == null){
                logger.logFailure(SourceType.MANUAL, "Control Rule " + name + " not found");
                return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            }
            logger.logSuccess(SourceType.MANUAL, controlRule);
            return Response.ok(controlRule).build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insertControlRule(String name) {
        if(name == null || name.trim().isEmpty()){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        try {
            controlRuleDao.addControlRule(name);
            logger.logSuccess(SourceType.MANUAL, "Control Rule " + name + " inserted");
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
    public Response deleteControlRule(String name) {
        if(name == null || name.trim().isEmpty()){
            logger.logFailure(SourceType.MANUAL, "Bad request");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        try {
            controlRuleDao.deleteControlRule(name);
            logger.logSuccess(SourceType.MANUAL, "Control Rule " + name + " deleted");
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            logger.logFailure(SourceType.MANUAL, ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }
}
