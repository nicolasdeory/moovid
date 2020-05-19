package aiss.model.photos.mediaitem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class MediaItem {
	private String id;
	private String description;
	private String productUrl;
	private String baseUrl;
	private String mimeType;
	private MediaMetadata mediaMetadata;
	private ContributorInfo contributorInfo;
	private String filename;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public MediaMetadata getMediaMetadata() {
		return mediaMetadata;
	}
	public void setMediaMetadata(MediaMetadata mediaMetadata) {
		this.mediaMetadata = mediaMetadata;
	}
	public ContributorInfo getContributorInfo() {
		return contributorInfo;
	}
	public void setContributorInfo(ContributorInfo contributorInfo) {
		this.contributorInfo = contributorInfo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "MediaItem [id=" + id + ", description=" + description + ", productUrl=" + productUrl + ", baseUrl="
				+ baseUrl + ", mimeType=" + mimeType + ", filename=" + filename + "]";
	}
	
	
	
	
}