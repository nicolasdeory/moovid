package aiss.luis.model;

import java.util.Map;

public class Intent {

	private IntentType topIntent;
	private Map<String, String> entities; 
	
	public Intent() {
		super();
	}

	public IntentType getTopIntent() {
		return topIntent;
	}

	public void setTopIntent(IntentType topIntent) {
		this.topIntent = topIntent;
	}

	@Override
	public String toString() {
		return "Intent [topIntent=" + topIntent + ", entities=" + entities + "]";
	}
	
	
}
