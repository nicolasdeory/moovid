package aiss.model.luis.classes;

import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.Sentiment;

public class Intent {

	protected IntentType topIntent;
	protected Sentiment sentiment;
	
	public Intent() {
		super();
		this.topIntent = IntentType.None;
		this.sentiment = Sentiment.neutral;
	}
	
	public Intent(IntentType topIntent) {
		this.topIntent = topIntent;
	}

	public Intent(IntentType topIntent, Sentiment sentiment) {
		this.topIntent = topIntent;
		this.sentiment = sentiment;
	}

	
	public IntentType getTopIntent() {
		return topIntent;
	}

	public void setTopIntent(IntentType topIntent) {
		this.topIntent = topIntent;
	}

	
	
	
}
