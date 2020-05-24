package aiss.montage;

import java.util.List;

public class MontageJobResult 
{
	private List<String> photoUrls;
	private List<String> musicUrls;
	private boolean isPending;
	
	public MontageJobResult(List<String> photoUrls, List<String> musicUrls) {
		super();
		this.photoUrls = photoUrls;
		this.musicUrls = musicUrls;
		this.isPending = false;
	}
	
	public MontageJobResult()
	{
		this.isPending = true;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<String> getMusicUrls() {
		return musicUrls;
	}

	public void setMusicUrls(List<String> musicUrls) {
		this.musicUrls = musicUrls;
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}
	
	
	
	
	
}
