package aiss.model.photos.mediaitem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import aiss.model.photos.filter.VideoProcessingStatus;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Video implements Media{
	private String cameraMake;
	private String cameraModel;
	private Float fps;
	private VideoProcessingStatus status;
	
	public String getCameraMake() {
		return cameraMake;
	}
	public void setCameraMake(String cameraMake) {
		this.cameraMake = cameraMake;
	}
	public String getCameraModel() {
		return cameraModel;
	}
	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}
	public Float getFps() {
		return fps;
	}
	public void setFps(Float fps) {
		this.fps = fps;
	}
	public VideoProcessingStatus getStatus() {
		return status;
	}
	public void setStatus(VideoProcessingStatus status) {
		this.status = status;
	}
	
	

}