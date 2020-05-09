package aiss.conversation.intenthandler;

import java.util.ArrayList;
import java.util.List;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.Context;
import aiss.model.luis.classes.Intent;

public class HelloHandler extends BaseIntentHandler {

	@Override
	public List<ChatQueryResponse> makeResponse() {
		List<ChatQueryResponse> l = new ArrayList<>();
		l.add(ChatQueryResponse.createChatResponse("holis")); // TODO: Chat response supplier
		return l;
	}

}
