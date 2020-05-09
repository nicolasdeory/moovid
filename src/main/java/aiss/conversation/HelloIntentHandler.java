package aiss.conversation;

import java.util.ArrayList;
import java.util.List;

import aiss.controller.main.ChatQueryResponse;
import aiss.model.luis.classes.Intent;

public class HelloIntentHandler implements IntentHandler {

	@Override
	public List<ChatQueryResponse> handleInput(Intent intent, Context context) {
		List<ChatQueryResponse> l = new ArrayList<>();
		l.add(ChatQueryResponse.createChatResponse("holis")); // TODO: Chat response supplier
		return l;
	}

}
