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
		// HELP
		case Help:
			return HelpHandler.generateResponse(context);
		// COMMUNICATION CANCEL
		case CommunicationCancel:
			return CancelIntentHandler.generateResponse(intent,context);
		// COMMUNICATION CONFIRM
		case CommunicationConfirm:
			if(context.getPreviousState().equals("did-you-want-montage") || context.getContextType().equals(ContextType.MontageTheme))
			{
				context = null;
				return CreateMontageHandler.generateMontage(intent, context, "AskTheme");
			}
			else if (context.getPreviousState().equals("montage-ask-music") || context.getContextType().equals(ContextType.MontageMusic))
			{
				context.setContextType(ContextType.MontageMusic);
				return CreateMontageHandler.generateMontage(intent, context, "AskMusic");
			}
			else 
			{
				return UnexpectedIntentHandler.generateResponse(context);
			}
		// CREATE MONTAGE
		case CreateMontage:
			return CreateMontageHandler.generateMontage(intent, context, "CreateMontage");
		// DECIDE FOR ME
		case DecideForMe:
			if(weAreMakingMontage(context))
			{
				return CreateMontageHandler.generateMontage(intent, context, "DecideForMe");
			} else 
			{
				return UnexpectedIntentHandler.generateResponse(context);
			}
		// GREETING	
		case Greeting:
			return GenericHandler.generateResponse("hello-intent", context);
		// MONTAGE THEME INTENT
		case MontageThemeIntent:
			if(weAreMakingMontage(context))
			{
				return CreateMontageHandler.generateMontage(intent, context, "MontageTheme");
			} else 
			{
				return GenericHandler.generateResponse("did-you-want-montage", context);
			}
		// MUSIC DESCRIPTION
		case MusicDescription:
			if (weAreMakingMontage(context))
			{
				return CreateMontageHandler.generateMontage(intent, context, "MusicDescription");
			} else 
			{
				return GenericHandler.generateResponse("did-you-want-montage", context);
			}
		// NONE INTENT
		case None:
			return GenericHandler.generateResponse("unknown-intent", context);
		// SPECIFIC THEME
		case SpecificTheme:
			if (weAreMakingMontage(context))
			{
				return CreateMontageHandler.generateMontage(intent, context, "MontageTheme");
			} else 
			{
				return GenericHandler.generateResponse("did-you-want-montage", context);
			}
		case Insult:
			return GenericHandler.generateResponse("insult", context);
		case No:
			if (weAreMakingMontage(context))
			{
				return CreateMontageHandler.generateMontage(intent, context, "DecideForMe");
			}
			else
			{
				context = null;
				return GenericHandler.generateResponse("ok", context);
			}
		default:
			return null;
		}
	}
	
}
