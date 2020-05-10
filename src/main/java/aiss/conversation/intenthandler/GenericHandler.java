package aiss.conversation.intenthandler;

import java.util.ArrayList;
import java.util.List;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.ChatResponseSupplier;
import aiss.conversation.Context;
import aiss.model.luis.classes.Intent;

/**
 * Handler for basic chat responses without context (E.g. Hello, Thanks)
 *
 */
public class GenericHandler extends BaseIntentHandler {

	private String key;
	@Override
	public ChatQueryResponse generateResponse() {
		List<ChatQueryResponse> l = new ArrayList<>();
		String[] chatStrings = ChatResponseSupplier.getLocalizedResponse(key);
		ChatQueryResponse chatResp = ChatQueryResponse.createBasic(context);
		chatResp.addChatMessages(chatStrings);
		
		return chatResp;
	}
	
	public GenericHandler(String key, Intent intent, Context context)
	{
		super(intent, context);
		this.key = key;
	}

}
