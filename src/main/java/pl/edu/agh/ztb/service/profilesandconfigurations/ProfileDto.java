package pl.edu.agh.ztb.service.profilesandconfigurations;

public class ProfileDto {

	private String profileName;
	
	private String normName;
	
	private Double ambient;

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return the normName
	 */
	public String getNormName() {
		return normName;
	}

	/**
	 * @param normName the normName to set
	 */
	public void setNormName(String normName) {
		this.normName = normName;
	}

	/**
	 * @return the ambient
	 */
	public Double getAmbient() {
		return ambient;
	}

	/**
	 * @param ambient the ambient to set
	 */
	public void setAmbient(Double ambient) {
		this.ambient = ambient;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProfileDto [profileName=" + profileName + ", normName="
				+ normName + ", ambient=" + ambient + "]";
	}
	
}
