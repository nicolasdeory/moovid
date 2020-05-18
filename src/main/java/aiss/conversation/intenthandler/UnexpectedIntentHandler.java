package aiss.conversation.intenthandler;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.Context;
import aiss.conversation.ContextType;

/**
 * Handler for basic chat responses without context (E.g. Hello, Thanks)
 *
 */
public class UnexpectedIntentHandler {

	public static ChatQueryResponse generateResponse(Context context) {
		if (context.getContextType().equals(ContextType.MontageTheme))
		{
			return GenericHandler.generateResponse("unexpected-intent-in-theme", context);
		}
		else if (context.getContextType().equals(ContextType.MontageMusic))
		{
			return GenericHandler.generateResponse("unexpected-intent-in-music", context);
		}
		else 
		{
			return GenericHandler.generateResponse("unexpected-intent", context);
		}
	}

}
