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
	
	public static ChatQueryResponse createBasic()
	{
		return new ChatQueryResponse(ChatQueryResponseType.Basic);
	}
	
	public static ChatQueryResponse createWaitForInput()
	{
		return new ChatQueryResponse(ChatQueryResponseType.WaitForInput);
	}
	
	public static ChatQueryResponse createVideoGeneration(String jobId)
	{
		return new ChatQueryResponse(ChatQueryResponseType.VideoGeneration);
	}
	
	public void addChatMessage(String message)
	{
		messages.add(message);
	}
	
	private ChatQueryResponse(ChatQueryResponseType type) {
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
