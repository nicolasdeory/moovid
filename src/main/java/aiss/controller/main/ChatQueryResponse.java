package aiss.controller.main;

import java.util.List;

public class ChatQueryResponse 
{
	ChatQueryResponseType responseType; // finish, loading, waitforinput
	String message;
	List<String> imageLinks;
	String songLink;
	
	public static ChatQueryResponse createChatResponse(String message)
	{
		ChatQueryResponse response = new ChatQueryResponse();
		response.setResponseType(ChatQueryResponseType.Basic);
		response.setMessage(message);
	}
	
	public ChatQueryResponse() {
		//
	}
	
	public String getSongLink() {
		return songLink;
	}

	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}

	public ChatQueryResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ChatQueryResponseType responseType) {
		this.responseType = responseType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(List<String> imageLinks) {
		this.imageLinks = imageLinks;
	}
	
	public 
}
