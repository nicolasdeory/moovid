package aiss.conversation.intenthandler;

import java.util.ArrayList;

import aiss.controller.main.ChatQueryResponse;
import aiss.conversation.ChatResponseSupplier;
import aiss.conversation.Context;
import aiss.conversation.ContextType;
import aiss.model.luis.classes.Intent;
import aiss.model.luis.classes.MontageCreateIntent;
import aiss.model.luis.classes.MontageThemeIntent;
import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.montage.JobManager;
import aiss.montage.MontageJob;

public class CreateMontageHandler {

	public static ChatQueryResponse generateMontage(Intent intt, Context context, String type)
	{
		switch (type)
		{
			case "CreateMontage":
				return handleCreateMontage(intt, context);
			case "AskTheme":
				return handleAskTheme(intt, context);
			case "AskMusic":
				return handleAskMusic(intt, context);
			case "DecideForMe":
				return handleDecideForMe(intt, context);
			case "MusicDescription":
				return handleMusicDescription(intt, context);
			case "MontageTheme":
				return handleMontageTheme(intt, context);
			default:
				throw new UnsupportedOperationException();
		}
		
	}
	
	private static ChatQueryResponse handleCreateMontage(Intent intt, Context ctx) 
	{
		MontageCreateIntent intentMontage = (MontageCreateIntent)intt;
		Context myContext = new Context();
		ChatQueryResponse resp;
		
		if (!ctx.isLoggedIn())
		{
			resp = ChatQueryResponse.createBasic(intt, ctx);
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-login"));
			return resp;
		}
		
		if (intentMontage.getThemeEntities() != null && intentMontage.getThemeEntities().size() > 0)
		{
			myContext.setThemeEntities(intentMontage.getThemeEntities());
			myContext.setStart(intentMontage.getStart());
			myContext.setEnd(intentMontage.getEnd());
			myContext.setContextType(ContextType.MontageMusic);
			resp = ChatQueryResponse.createWaitForInput(intt, myContext);
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-music"));
			myContext.setStart(intentMontage.getStart());
			myContext.setEnd(intentMontage.getEnd());
		}
		else
		{
			myContext.setContextType(ContextType.MontageTheme);
			resp = ChatQueryResponse.createWaitForInput(intt, myContext);
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-theme"));
			if (myContext.getStart() == null && myContext.getEnd() == null)
			{
				myContext.setStart(intentMontage.getStart());
				myContext.setEnd(intentMontage.getEnd());
			}
			
		}
		return resp;
	}
	
	private static ChatQueryResponse handleMontageTheme(Intent intt, Context ctx)
	{
		MontageThemeIntent intentMontage = (MontageThemeIntent)intt; // TODO: We should probably deprecate MontageTheme...
		Context myContext = new Context();
		ChatQueryResponse resp;
		myContext.setContextType(ContextType.MontageMusic);
		myContext.setThemeEntities(intentMontage.getThemeEntities());
		resp = ChatQueryResponse.createWaitForInput(intt, myContext);
		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-music"));
		return resp;
	}
	
	private static ChatQueryResponse handleAskTheme(Intent intt, Context ctx)
	{
		ChatQueryResponse resp = ChatQueryResponse.createWaitForInput(intt, ctx);
		Context myContext = new Context();
		myContext.setContextType(ContextType.MontageTheme);
		resp = ChatQueryResponse.createWaitForInput(intt, myContext);
		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-indicate-theme"));
		return resp;
	}
	
	private static ChatQueryResponse handleAskMusic(Intent intt, Context ctx)
	{
		ChatQueryResponse resp;
		Context myContext = new Context();
		myContext.setContextType(ContextType.MontageMusic);
		resp = ChatQueryResponse.createWaitForInput(intt, myContext);
		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-indicate-music"));
		return resp;
	}
	
	private static ChatQueryResponse handleDecideForMe(Intent intt, Context ctx)
	{
		ChatQueryResponse resp;
		//Context myContext = new Context();
		//myContext.set
		if (ctx.getContextType().equals(ContextType.MontageTheme))
		{
			ctx.setThemeEntities(new ArrayList<MontageTheme>());
			ctx.setContextType(ContextType.MontageMusic);
			resp = ChatQueryResponse.createWaitForInput(intt, ctx);
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-ask-music"));
			return resp;
		}
		else if (ctx.getContextType().equals(ContextType.MontageMusic))
		{
			MontageJob job = MontageJob.of(ctx.getAccessToken(), ctx.getThemeEntities(), null, ctx.getStart(), ctx.getEnd());
			JobManager.enqueueJob(job);
			resp = ChatQueryResponse.createVideoGeneration(intt, job.getUuid().toString());
			resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-start-processing"));
			return resp;
		}
		return null;
	}
	
	private static ChatQueryResponse handleMusicDescription(Intent intt, Context ctx)
	{
		MusicIntent musicIntent = (MusicIntent)intt;
		MontageJob job = MontageJob.of(ctx.getAccessToken(), ctx.getThemeEntities(), musicIntent, ctx.getStart(), ctx.getEnd());
		JobManager.enqueueJob(job);
		ChatQueryResponse resp = ChatQueryResponse.createVideoGeneration(intt, job.getUuid().toString());
		resp.addChatMessages(ChatResponseSupplier.getLocalizedResponse("montage-start-processing"));
		return resp;
	}
	
}
