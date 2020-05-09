package aiss.conversation.intenthandler;

import aiss.conversation.Context;
import aiss.model.luis.classes.Intent;

public abstract class BaseIntentHandler implements IntentHandler {

	protected Intent intent;
	protected Context context;
	
	public BaseIntentHandler(Intent intent, Context context)
	{
		this.intent = intent;
		this.context = context;
	}
	
	public BaseIntentHandler() 
	{
		throw new UnsupportedOperationException("You must use the constructor with parameters.");
	}
}
