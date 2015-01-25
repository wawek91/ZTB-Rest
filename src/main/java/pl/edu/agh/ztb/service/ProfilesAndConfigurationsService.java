package pl.edu.agh.ztb.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import loggers.impl.RestLogger;
import project.dao.ProfilesAndConfigurationsDAOImpl;
import project.dao.data.Configuration;
import project.dao.data.Profile;
import project.dao.interfaces.ProfilesAndConfigurationsDAO;

/**
 * Profiles and configurations service
 * @author £ukasz.Gruba
 */
@Path(value = "/profilesAndConfigurations")
public class ProfilesAndConfigurationsService {
	//TODO: log events
	//TODO: add support for map params
	//TODO: postmaster TCP/IP ???
	/**
	 * Logger
	 */
	public RestLogger logger = new RestLogger();
	
	@GET
	@Path(value = "/selectProfileByName/{profileName}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfileByName(@PathParam(value = "profileName") String profileName) {
		
		try {
			Profile profile = ProfilesAndConfigurationsDaoManager.getDao().getProfile(profileName);
			return Response.ok(profile).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/selectProfileByID/{profileID}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfileByID(@PathParam(value = "profileID") Long profileID) {
		
		try {
			Profile profile = ProfilesAndConfigurationsDaoManager.getDao().getProfile(profileID);
			return Response.ok(profile).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/selectProfilesList")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfilesList() {
		
		try {
			List<Profile> profilesList = ProfilesAndConfigurationsDaoManager.getDao().getProfilesList();
			return Response.ok(profilesList).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/createProfile/{profileName}/{normName}/{ambient}")
	public Response addProfile(
			@PathParam(value = "profileName") String profileName,
			@PathParam(value = "normName") String normName,
			@PathParam(value = "ambient") Double ambient) {
		
		try {
			ProfilesAndConfigurationsDaoManager.getDao().addProfile(profileName, normName, ambient);
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/updateProfile/{profileName}/{normName}/{ambient}")
	public Response updateProfile(
			@PathParam(value = "profileName") String profileName,
			@PathParam(value = "normName") String normName,
			@PathParam(value = "ambient") Double ambient) {
		
		try {
			ProfilesAndConfigurationsDaoManager.getDao().updateProfile(profileName, normName, ambient);
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path(value = "/deleteProfile/{profileName}")
	public Response deleteProfile(@PathParam(value = "profileName") String profileName) {
		
		try {
			ProfilesAndConfigurationsDaoManager.getDao().deleteProfile(profileName);
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/selectProfilesForSegment/{segmentID}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfilesForSegment(@PathParam(value = "segmentID") Long segmentID) {
		
		try {
			List<Profile> profiles = ProfilesAndConfigurationsDaoManager.getDao().getProfilesForSegment(segmentID);
			return Response.ok(profiles).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/selectConfigurationForSegment/{segmentID}/{profileName}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectConfigForSegment(
			@PathParam(value = "segmentID") Long segmentID,
			@PathParam(value = "profileName") String profileName) {
		
		try {
			Configuration configuration = ProfilesAndConfigurationsDaoManager.getDao().getConfigForSegment(segmentID, profileName);
			return Response.ok(configuration).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
//	@POST
//	@Path(value = "/createConfigurationForSegment/{segmentID}/{profileName}/{lamps}")
//	public Response addConfigForSegment(
//			@PathParam(value = "segmentID") Long segmentID,
//			@PathParam(value = "profileName") String profileName,
//			@PathParam(value = "lamps") Map<Long, Double> lamps) {
//		
//		ProfilesAndConfigurationsDaoManager.getDao().addConfigForSegment(segmentID, profileName, lamps);
//	}
	
//	@POST
//	@Path(value = "/updateConfigurationForSegment/{segmentID}/{profileName}/{lampsList}")
//	public Response updateConfigForSegment(
//			@PathParam(value = "segmentID") Long segmentID,
//			@PathParam(value = "profileName") String profileName,
//			@PathParam(value = "lamps") Map<Long, Double> lamps) {
//		
//		ProfilesAndConfigurationsDaoManager.getDao().updateConfigForSegment(segmentID, profileName, lamps);
//	}
	
	@DELETE
	@Path(value = "/deleteConfigurationForSegment/{segmentID}/{profileName}")
	public Response deleteConfigForSegment(
			@PathParam(value = "segmentID") Long segmentID,
			@PathParam(value = "profileName") String profileName) {
		
		try {
			ProfilesAndConfigurationsDaoManager.getDao().deleteConfigForSegment(segmentID, profileName);
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	/**
	 * inner DAO manager
	 * @author £ukasz.Gruba
	 */
	private static class ProfilesAndConfigurationsDaoManager { 
		
		/**
		 * DAO instance
		 */
		private static ProfilesAndConfigurationsDAO daoInstance;
		
		/**
		 * Private constructor
		 */
		private ProfilesAndConfigurationsDaoManager() {
		}
		
		/**
		 * Returns DAO instance
		 * @return
		 */
		public static ProfilesAndConfigurationsDAO getDao() {
			
			if(daoInstance == null) {
				daoInstance = new ProfilesAndConfigurationsDAOImpl("ztb6profiles", "user1", "user1", "localhost", "5432");
			}
			return daoInstance;
		}
	}
}
