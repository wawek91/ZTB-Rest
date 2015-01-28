package pl.edu.agh.ztb.service;

import java.util.List;

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

import agh.db.ControlRuleDao;
import agh.db.ControlRuleDaoImpl;

@Path("/control_rules")
public class ControlRulesService {

    private RestLogger logger;
    
    public ControlRulesService() {
    	ApplicationContext springContext = new ClassPathXmlApplicationContext("ztb7-context.xml");
		logger = springContext.getBean(RestLogger.class);
    }
    
    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getControlRules() {
        ControlRuleDao controlRuleDao = new ControlRuleDaoImpl();
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
        ControlRuleDao controlRuleDao = new ControlRuleDaoImpl();
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
        ControlRuleDao controlRuleDao = new ControlRuleDaoImpl();
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
        ControlRuleDao controlRuleDao = new ControlRuleDaoImpl();
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
