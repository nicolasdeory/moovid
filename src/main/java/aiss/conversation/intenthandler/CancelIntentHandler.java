package aiss.conversation.intenthandler;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.ChatResponseSupplier;
import aiss.conversation.Context;
import aiss.model.luis.classes.Intent;

public class CancelIntentHandler {

	public static ChatQueryResponse generateResponse(Intent intent, Context context) {
		ChatQueryResponse resp = ChatQueryResponse.createBasic(intent, null);
		if (context == null)
		{
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("cancel-nothing"));
		}
		else 
		{
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("cancel"));
		}
		return resp;
	}

}
