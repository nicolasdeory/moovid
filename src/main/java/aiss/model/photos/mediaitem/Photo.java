package aiss.model.photos.mediaitem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Photo implements Media{
	private String cameraMake;
	private String cameraModel;
	private Float focalLength;
	private Float apertureFNumber;
	private Float isoEquivalent;
	private String exposureTime;
	
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
	public Float getFocalLength() {
		return focalLength;
	}
	public void setFocalLength(Float focalLength) {
		this.focalLength = focalLength;
	}
	public Float getApertureFNumber() {
		return apertureFNumber;
	}
	public void setApertureFNumber(Float apertureFNumber) {
		this.apertureFNumber = apertureFNumber;
	}
	public Float getIsoEquivalent() {
		return isoEquivalent;
	}
	public void setIsoEquivalent(Float isoEquivalent) {
		this.isoEquivalent = isoEquivalent;
	}
	public String getExposureTime() {
		return exposureTime;
	}
	public void setExposureTime(String exposureTime) {
		this.exposureTime = exposureTime;
	}
	
	
}