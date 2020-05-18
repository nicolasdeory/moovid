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
public class GenericHandler {

	public static ChatQueryResponse generateResponse(Intent intent, String key, Context context) {
		String[] chatStrings = ChatResponseSupplier.getLocalizedResponse(key);
		ChatQueryResponse chatResp = ChatQueryResponse.createBasic(intent, context);
		chatResp.addChatMessages(chatStrings);
		chatResp.getContext().setPreviousState(key);
		return chatResp;
	}

}
