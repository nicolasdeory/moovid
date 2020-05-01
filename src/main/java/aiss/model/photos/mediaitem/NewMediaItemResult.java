package aiss.model.photos.mediaitem;

import com.google.appengine.repackaged.com.google.rpc.Status;

public class NewMediaItemResult {
	private String uploadToken;
	private Status status;
	private MediaItem mediaItem;
	
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public MediaItem getMediaItem() {
		return mediaItem;
	}
	public void setMediaItem(MediaItem mediaItem) {
		this.mediaItem = mediaItem;
	}
	
	
}
