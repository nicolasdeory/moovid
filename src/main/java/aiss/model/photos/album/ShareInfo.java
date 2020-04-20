package aiss.model.photos.album;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class ShareInfo {
	private SharedAlbumOptions sharedAlbumOptions;
	private String shareableUrl;
	private String shareToken;
	private Boolean isJoined;
	private Boolean isOwned;
	
	public SharedAlbumOptions getSharedAlbumOptions() {
		return sharedAlbumOptions;
	}
	public void setSharedAlbumOptions(SharedAlbumOptions sharedAlbumOptions) {
		this.sharedAlbumOptions = sharedAlbumOptions;
	}
	public String getShareableUrl() {
		return shareableUrl;
	}
	public void setShareableUrl(String shareableUrl) {
		this.shareableUrl = shareableUrl;
	}
	public String getShareToken() {
		return shareToken;
	}
	public void setShareToken(String shareToken) {
		this.shareToken = shareToken;
	}
	public Boolean getIsJoined() {
		return isJoined;
	}
	public void setIsJoined(Boolean isJoined) {
		this.isJoined = isJoined;
	}
	public Boolean getIsOwned() {
		return isOwned;
	}
	public void setIsOwned(Boolean isOwned) {
		this.isOwned = isOwned;
	}
	
	
}