package aiss.conversation;

import java.util.List;

import aiss.controller.main.ChatQueryResponse;
import aiss.model.luis.classes.Intent;
import aiss.conversation.Context;

public interface IntentHandler 
{
	List<ChatQueryResponse> handleInput(Intent intent, Context context);
}
