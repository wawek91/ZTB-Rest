package pl.edu.agh.ztb.service;

import agh.db.ControlRuleDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/control_rules")
public class ControlRulesService {

    @GET
    @Path("/select")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getControlRules() {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        List<String> controlRuleList = controlRuleDao.getAllControlRules();
        return controlRuleList;
    }

    @GET
    @Path("/select/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getControlRuleByName(@PathParam("name") String name) {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        String controlRule = controlRuleDao.getControlRule(name);
        return controlRule;
    }

    @PUT
    @Path("/insert")
    public void insertControlRule(@FormParam("name") String name) {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        controlRuleDao.addControlRule(name);
    }

    @DELETE
    @Path("/delete")
    public void deleteControlRule(@FormParam("name") String name) {
        ControlRuleDaoImpl controlRuleDao = new ControlRuleDaoImpl();
        controlRuleDao.deleteControlRule(name);
    }
}
