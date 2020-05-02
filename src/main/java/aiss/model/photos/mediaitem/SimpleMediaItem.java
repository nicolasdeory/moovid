package aiss.model.photos.mediaitem;

public class SimpleMediaItem {
	private String uploadToken;
	private String fileName;
	
	public SimpleMediaItem(String uploadToken, String fileName) {
		super();
		this.uploadToken = uploadToken;
		this.fileName = fileName;
	}
	
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
