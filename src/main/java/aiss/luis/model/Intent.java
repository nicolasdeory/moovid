package aiss.luis.model;

import java.time.LocalDate;

public class Intent {

	protected IntentType topIntent;
	protected LocalDate start;
	protected LocalDate end;
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
