package aiss.conversation.intenthandler;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.ChatResponseSupplier;
import aiss.conversation.Context;
import aiss.conversation.ContextType;
import aiss.model.luis.classes.Intent;

public class CreateMontageHandler extends BaseIntentHandler {

	@Override
	public ChatQueryResponse generateResponse() {
		if (context == null)
		{
			Context ctx = new Context();
			ctx.setContext(ContextType.MontageTheme);
			ChatQueryResponse resp = ChatQueryResponse.createWaitForInput(ctx);
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-theme"));
			return resp;
		}
		else if (context.getContextType().equals(ContextType.MontageTheme))
		{
			Context ctx = new Context();
			ctx.setContext(ContextType.MontageMusic);
			ChatQueryResponse resp = ChatQueryResponse.createWaitForInput(ctx);
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-music"));
			return resp;
		}
		else if (context.getContextType().equals(ContextType.MontageMusic))
		{
			return generateMontage(this.intent, this.context);
		}
	}
	
	public static ChatQueryREsponse
	
	public static ChatQueryResponse generateMontage(Intent intt, Context context, String type)
	{
		switch (type)
		{
			case "CreateMontage":
				break;
			case "CreateMontage":
				break;
			case "CreateMontage":
				break;
			case "CreateMontage":
				break;
			case "CreateMontage":
				break;
			case "CreateMontage":
				break;
		}
		
	}
	
//	Context ctx;
//	if (context == null)
//	{
//		ctx = new Context();
//		// TODO: set date here
//		ctx.setContext(ContextType.MontageTheme);
//		ChatQueryResponse resp = ChatQueryResponse.createWaitForInput(ctx);
//		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-theme"));
//		return resp;
//	}
//	else if (context.getContextType().equals(ContextType.MontageTheme))
//	{
//		ctx = new Context();
//		ctx.setContext(ContextType.MontageMusic);
//		ChatQueryResponse resp = ChatQueryResponse.createWaitForInput(ctx);
//		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-music"));
//		return resp;
//	}
//	else if (context.getContextType().equals(ContextType.MontageMusic))
//	{
//		return generateMontage(this.intent, this.context);
//	}
//	
//	// 
//	ChatQueryResponse resp = ChatQueryResponse.createVideoGeneration(jobId)
//	resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-start-processing"));
//	return resp;
	
	public static ChatQueryResponse withTheme(Intent intent, Context context)
	{
		
	}
	
	public static ChatQueryResponse withMusic(Intent intent, Context context) 
	{
		
	}
	
	
	public CreateMontageHandler(Intent intent, Context context)
	{
		super(intent, context);
	}

}
