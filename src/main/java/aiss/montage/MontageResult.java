package aiss.montage;

import java.util.List;

public class MontageResult 
{
	private List<String> photoUrls;
	private String musicUrl;
	private boolean isPending;
	
	public MontageResult(List<String> photoUrls, String musicUrl) {
		super();
		this.photoUrls = photoUrls;
		this.musicUrl = musicUrl;
		this.isPending = false;
	}
	
	public MontageResult()
	{
		this.isPending = true;
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

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}
	
	
	
	
	
}
