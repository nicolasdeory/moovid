package aiss.luis.model.classes;

import java.time.LocalDate;

import aiss.luis.model.enumerates.IntentType;
import aiss.luis.model.enumerates.Sentiment;

public class Intent {

	protected IntentType topIntent;
	protected Sentiment sentiment;
	
	public Intent() {
		super();
		topIntent = IntentType.None;
		
	}

	public IntentType getTopIntent() {
		return topIntent;
	}

	public void setTopIntent(IntentType topIntent) {
		this.topIntent = topIntent;
	}

	
	
	
}
