package aiss.montage;

import java.util.List;

public class MontageResult 
{
	private List<String> photoUrls;
	private String musicUrl;
	
	public MontageResult(List<String> photoUrls, String musicUrl) {
		super();
		this.photoUrls = photoUrls;
		this.musicUrl = musicUrl;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	
	
	
}
