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

	@Override
	public String toString() {
		return "Intent [topIntent=" + topIntent + ", sentiment=" + sentiment + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sentiment == null) ? 0 : sentiment.hashCode());
		result = prime * result + ((topIntent == null) ? 0 : topIntent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intent other = (Intent) obj;
		if (sentiment != other.sentiment)
			return false;
		if (topIntent != other.topIntent)
			return false;
		return true;
	}

	
	
}
