package aiss.controller.main;

import java.util.ArrayList;
import java.util.List;

import aiss.conversation.Context;
import aiss.model.luis.classes.Intent;

public class ChatQueryResponse 
{
	List<String> messages;
	ChatQueryResponseType responseType;
	String jobId;
	Context context;
	Intent topIntent;
	String query;
	
	/**
	 * Creates a basic query response with null context and no extra data
	 */
	public static ChatQueryResponse createBasic(Intent topIntent, Context context)
	{
		return new ChatQueryResponse(ChatQueryResponseType.Basic, topIntent, context);
	}
	
	public static ChatQueryResponse createWaitForInput(Intent topIntent, Context context)
	{
		return new ChatQueryResponse(ChatQueryResponseType.WaitForInput, topIntent, context);
	}
	
	public static ChatQueryResponse createVideoGeneration(Intent topIntent, String jobId)
	{
		return new ChatQueryResponse(ChatQueryResponseType.VideoGeneration, jobId);
	}
	
	public void addChatMessage(String message)
	{
		messages.add(message.trim());
	}
	
	public void addChatMessages(String[] messageArray)
	{	
		for(String message : messageArray) 
		{
			this.messages.add(message.trim());
		}
	}
	
	private ChatQueryResponse(ChatQueryResponseType type, Intent intn, Context context) {
		this.context = context;
		this.responseType = type;
		this.topIntent = intn;
		this.messages = new ArrayList<String>();
	}
	
	private ChatQueryResponse(ChatQueryResponseType type, String jobId)
	{
		this.context = null;
		this.messages = new ArrayList<String>();
		this.responseType = type;
		this.jobId = jobId;
	}

	public ChatQueryResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ChatQueryResponseType responseType) {
		this.responseType = responseType;
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<String> getMessages() {
		return messages;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Intent getTopIntent() {
		return topIntent;
	}

	public void setTopIntent(Intent topIntent) {
		this.topIntent = topIntent;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String originalQuery) {
		this.query = originalQuery;
	}
	
	
	

}
