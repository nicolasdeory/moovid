package aiss.controller.main;

import java.util.ArrayList;
import java.util.List;

import aiss.conversation.Context;

public class ChatQueryResponse 
{
	List<String> messages;
	ChatQueryResponseType responseType;
	Integer jobId;
	Context context;
	
	/**
	 * Creates a basic query response with null context and no extra data
	 */
	public static ChatQueryResponse createBasic(Context context)
	{
		return new ChatQueryResponse(ChatQueryResponseType.Basic, context);
	}
	
	public static ChatQueryResponse createWaitForInput(Context context)
	{
		return new ChatQueryResponse(ChatQueryResponseType.WaitForInput, context);
	}
	
	public static ChatQueryResponse createVideoGeneration(String jobId)
	{
		return new ChatQueryResponse(ChatQueryResponseType.VideoGeneration,null);
	}
	
	public void addChatMessage(String message)
	{
		messages.add(message);
	}
	
	public void addChatMessages(String[] messageArray)
	{
		for(String message : messageArray) 
		{
			this.messages.add(message);
		}
	}
	
	private ChatQueryResponse(ChatQueryResponseType type, Context context) {
		this.context = context;
		this.responseType = type;
		this.messages = new ArrayList<String>();
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

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

}
