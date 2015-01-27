package pl.edu.agh.ztb.service.profilesandconfigurations;

import java.util.Map;

public class SegmentDto {
	
	private Long segmentId;
	
	private String profileName;
	
	private Map<Long, Double> lamps;

	/**
	 * @return the segmentId
	 */
	public Long getSegmentId() {
		return segmentId;
	}

	/**
	 * @param segmentId the segmentId to set
	 */
	public void setSegmentId(Long segmentId) {
		this.segmentId = segmentId;
	}

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
	 * @return the lamps
	 */
	public Map<Long, Double> getLamps() {
		return lamps;
	}

	/**
	 * @param lamps the lamps to set
	 */
	public void setLamps(Map<Long, Double> lamps) {
		this.lamps = lamps;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegmentDto [segmentId=" + segmentId + ", profileName="
				+ profileName + ", lamps=" + lamps + "]";
	}
}
