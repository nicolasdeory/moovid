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
	
	public static ChatQueryResponse generateMontage(Intent intt, Context ctx)
	{
		// 
		ChatQueryResponse resp = ChatQueryResponse.createVideoGeneration(jobId)
		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-start-processing"));
		return resp;
	}
	
	
	public CreateMontageHandler(Intent intent, Context context)
	{
		super(intent, context);
	}

}
