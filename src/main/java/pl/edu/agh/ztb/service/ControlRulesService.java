package pl.edu.agh.ztb.service;

import agh.db.ControlRuleDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/control_rules")
public class ControlRulesService {

    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getControlRules() {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        List<String> controlRuleList;
        try{
            controlRuleList = controlRuleDao.getAllControlRules();
            return Response.ok(controlRuleList).build();
        }
        catch(Exception ex){
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
                return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            }
            return Response.ok(controlRule).build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @PUT
    @Path("/insert")
    public Response insertControlRule(@FormParam("name") String name) {
        if(name == null || name.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        try {
            controlRuleDao.addControlRule(name);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }
    }

    @DELETE
    @Path("/delete")
    public Response deleteControlRule(@FormParam("name") String name) {
        if(name == null || name.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad parameter").build();
        }
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        try {
            controlRuleDao.deleteControlRule(name);
            return Response.ok("OK").build();
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception thrown").build();
        }

    }
}
