package aiss.conversation.intenthandler;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.ChatResponseSupplier;
import aiss.conversation.Context;
import aiss.model.luis.classes.Intent;

public class CancelIntentHandler extends BaseIntentHandler {

	@Override
	public ChatQueryResponse generateResponse() {
		ChatQueryResponse resp = ChatQueryResponse.createBasic(null);
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
	
	public CancelIntentHandler(Intent intent, Context context)
	{
		super(intent, context);
	}

}
