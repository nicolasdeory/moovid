package aiss.conversation;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.intenthandler.*;
import aiss.model.luis.classes.Intent;

public class IntentHandlerFactory {

	private static boolean weAreMakingMontage(Context context)
	{
		return context.getContextType().equals(ContextType.MontageTheme)
				|| context.getContextType().equals(ContextType.MontageMusic);
	}
	
	public static ChatQueryResponse fromIntent(Intent intent, Context context)
	{
		switch (intent.getTopIntent())
		{
		case CommunicationCancel:
			return CancelIntentHandler.generateResponse(intent,context);
		case CommunicationConfirm:
			if(context.getPreviousState().equals("did-you-want-montage"))
			{
				context = null;
				return CreateMontageHandler.generateMontage(intent, context);
			}
			else if (context.getPreviousState().equals(anObject))
			{
				return GenericHandler.generateResponse("unexpected-intent", context);
			}
			else 
			{
				return GenericHandler.generateResponse("unexpected-intent", context);
			}
		case CreateMontage:
			return CreateMontageHandler.generateMontage(intent, context);
		case DecideForMe:
			if(weAreMakingMontage(context))
			{
				context.set
				return CreateMontageHandler.generateMontage(intent, context);
			} else 
			{
				return GenericHandler.generateResponse("unexpected-intent", context);
			}
			
		case Greeting:
			return GenericHandler.generateResponse("hello-intent", context);
		case MontageThemeIntent:
			return new CancelIntentHandler(intent, context);
		case MusicDescription:
			if (weAreMakingMontage(context))
			{
				
			} else 
			{
				return GenericHandler.generateResponse("did-you-want-montage", context);
			}
		case None:
			return GenericHandler.generateResponse("unknown-intent", context);
		case SpecificTheme:
			if (weAreMakingMontage(context))
			{
				
			} else 
			{
				return GenericHandler.generateResponse("did-you-want-montage", context);
			}
			return new CancelIntentHandler(intent, context);
		default:
			return null;
		}
	}
	
}
