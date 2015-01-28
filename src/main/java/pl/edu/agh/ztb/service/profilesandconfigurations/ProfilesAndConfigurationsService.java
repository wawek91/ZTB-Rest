package pl.edu.agh.ztb.service.profilesandconfigurations;

import java.util.List;

import javax.ws.rs.Consumes;
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

import project.dao.ProfilesAndConfigurationsDAOImpl;
import project.dao.data.Configuration;
import project.dao.data.Profile;
import project.dao.interfaces.ProfilesAndConfigurationsDAO;

/**
 * Profiles and configurations service
 * @author Lukasz.Gruba
 */
@Path(value = "/profiles_and_configurations")
public class ProfilesAndConfigurationsService {

	/**
	 * Logger
	 */
	public RestLogger logger;
	
	public ProfilesAndConfigurationsService() {
		ApplicationContext springContext = new ClassPathXmlApplicationContext("ztb7-context.xml");
		logger = springContext.getBean(RestLogger.class);
	}
	
	private static final String SERVICE_NAME_PREFIX = "Executed service: /profiles_and_configurations/";
	
	@GET
	@Path(value = "/select_profile_by_name/{profileName}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfileByName(@PathParam(value = "profileName") String profileName) {
		
		String serviceName = "select_profile_by_name";
		try {
			Profile profile = ProfilesAndConfigurationsDaoManager.getDao().getProfile(profileName);
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, profileName));
			return Response.ok(profile).build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, profileName));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/select_profile_by_id/{profileID}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfileByID(@PathParam(value = "profileID") Long profileID) {
		
		String serviceName = "select_profile_by_id";
		try {
			Profile profile = ProfilesAndConfigurationsDaoManager.getDao().getProfile(profileID);
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, profileID));
			return Response.ok(profile).build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, profileID));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/select_profiles_list")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfilesList() {
		
		String serviceName = "select_profiles_list";
		try {
			List<Profile> profilesList = ProfilesAndConfigurationsDaoManager.getDao().getProfilesList();
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null));
			return Response.ok(profilesList).build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/create_profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response addProfile(ProfileDto profile) {
		
		String serviceName = "create_profile";
		try {
			ProfilesAndConfigurationsDaoManager.getDao().addProfile(profile.getProfileName(), profile.getNormName(), profile.getAmbient());
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, profile));
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, profile));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/update_profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response updateProfile(ProfileDto profile) {
		
		String serviceName = "update_profile";
		try {
			ProfilesAndConfigurationsDaoManager.getDao().updateProfile(profile.getProfileName(), profile.getNormName(), profile.getAmbient());
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, profile));
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, profile));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/delete_profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteProfile(String profileName) {
		
		String serviceName = "delete_profile";
		try {
			ProfilesAndConfigurationsDaoManager.getDao().deleteProfile(profileName);
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, profileName));
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, profileName));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/select_profiles_for_segment/{segmentID}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectProfilesForSegment(@PathParam(value = "segmentID") Long segmentID) {
		
		String serviceName = "select_profiles_for_segment";
		try {
			List<Profile> profiles = ProfilesAndConfigurationsDaoManager.getDao().getProfilesForSegment(segmentID);
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, segmentID));
			return Response.ok(profiles).build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, segmentID));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path(value = "/select_configuration_for_segment/{segmentID}/{profileName}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response selectConfigForSegment(
			@PathParam(value = "segmentID") Long segmentID,
			@PathParam(value = "profileName") String profileName) {
		
		String serviceName = "select_configuration_for_segment";
		try {
			Configuration configuration = ProfilesAndConfigurationsDaoManager.getDao().getConfigForSegment(segmentID, profileName);
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, segmentID, profileName));
			return Response.ok(configuration).build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, segmentID, profileName));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/create_configuration_for_segment")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response addConfigForSegment(SegmentDto segment) {
		
		String serviceName = "create_configuration_for_segment";
		try {
			ProfilesAndConfigurationsDaoManager.getDao().addConfigForSegment(segment.getSegmentId(), segment.getProfileName(), segment.getLamps());
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, segment));
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, e, segment));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/update_configuration_for_segment")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response updateConfigForSegment(SegmentDto segment) {
		
		String serviceName = "update_configuration_for_segment";
		try {
			ProfilesAndConfigurationsDaoManager.getDao().updateConfigForSegment(segment.getSegmentId(), segment.getProfileName(), segment.getLamps());
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, segment));
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, e, segment));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path(value = "/delete_configuration_for_segment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteConfigForSegment(SegmentDto segment) {
		
		String serviceName = "delete_configuration_for_segment";
		try {
			ProfilesAndConfigurationsDaoManager.getDao().deleteConfigForSegment(segment.getSegmentId(), segment.getProfileName());
			logger.logSuccess(SourceType.MANUAL, getLogMessage(serviceName, null, segment));
			return Response.ok("OK").build();
		}
		catch(Exception e) {
			logger.logFailure(SourceType.MANUAL, getLogMessage(serviceName, e, segment));
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	/**
	 * Creates log message
	 * @param serviceName
	 * @param e
	 * @param params
	 * @return
	 */
	private String getLogMessage(String serviceName, Exception e, Object... params) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(SERVICE_NAME_PREFIX);
		if(params != null) {
			for(Object o : params) {
				sb.append("/");
				sb.append(o.toString());
			}
		}
		if(e != null) {
			sb.append(" Exception was thrown: ");
			sb.append(e.getMessage());
		}
		
		return sb.toString();
	}
	
	/**
	 * inner DAO manager
	 * @author Lukasz.Gruba
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
		 * @throws ClassNotFoundException 
		 */
		public static ProfilesAndConfigurationsDAO getDao() throws ClassNotFoundException {
			
			if(daoInstance == null) {
				daoInstance = new ProfilesAndConfigurationsDAOImpl("ztb6profiles", "user1", "user1", "localhost", "5432");
			}
			return daoInstance;
		}
	}
}
