package aiss.model.photos.mediaitem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class ContributorInfo {
	
	private String profilePictureBaseUrl;
	private String displayName;
	
	public String getProfilePictureBaseUrl() {
		return profilePictureBaseUrl;
	}
	public void setProfilePictureBaseUrl(String profilePictureBaseUrl) {
		this.profilePictureBaseUrl = profilePictureBaseUrl;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	

}