package aiss.conversation;

import java.util.List;

public class MontageJobData {
	Integer jobId;
	List<String> imageLinks;
	String songLink;
	
	public MontageJobData(Integer jobId, List<String> imageLinks, String songLink)
	{
		this.jobId = jobId;
		this.imageLinks = imageLinks;
		this.songLink = songLink;
	}
}
