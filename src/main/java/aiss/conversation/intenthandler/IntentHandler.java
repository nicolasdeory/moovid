package aiss.conversation.intenthandler;

import java.util.List;

import aiss.controller.main.ChatQueryResponse;
import aiss.model.luis.classes.Intent;
import aiss.conversation.Context;

public interface IntentHandler 
{
	ChatQueryResponse generateResponse();
}
