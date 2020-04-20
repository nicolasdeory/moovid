package aiss.model.photos.album;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Album {
	
	private String id;
	private String title;
	private String productUrl;
	private Boolean isWriteable;
	private ShareInfo shareInfo;
	private String mediaItemsCount;
	private String coverPhotoBaseUrl;
	private String coverPhotoMediaItemId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public Boolean getIsWriteable() {
		return isWriteable;
	}
	public void setIsWriteable(Boolean isWriteable) {
		this.isWriteable = isWriteable;
	}
	public ShareInfo getShareInfo() {
		return shareInfo;
	}
	public void setShareInfo(ShareInfo shareInfo) {
		this.shareInfo = shareInfo;
	}
	public String getMediaItemsCount() {
		return mediaItemsCount;
	}
	public void setMediaItemsCount(String mediaItemsCount) {
		this.mediaItemsCount = mediaItemsCount;
	}
	public String getCoverPhotoBaseUrl() {
		return coverPhotoBaseUrl;
	}
	public void setCoverPhotoBaseUrl(String coverPhotoBaseUrl) {
		this.coverPhotoBaseUrl = coverPhotoBaseUrl;
	}
	public String getCoverPhotoMediaItemId() {
		return coverPhotoMediaItemId;
	}
	public void setCoverPhotoMediaItemId(String coverPhotoMediaItemId) {
		this.coverPhotoMediaItemId = coverPhotoMediaItemId;
	}
	
	
}