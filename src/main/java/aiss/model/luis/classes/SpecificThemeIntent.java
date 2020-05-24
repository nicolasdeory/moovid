package aiss.model.luis.classes;

import java.util.List;

import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.model.luis.enumerates.Sentiment;

public class SpecificThemeIntent extends Intent{
	protected List<MontageTheme> themeEntities;
	
	public SpecificThemeIntent() {
		super(IntentType.SpecificTheme);
	}

	public SpecificThemeIntent(Sentiment sentiment, List<MontageTheme> themeEntities) {
		super(IntentType.SpecificTheme, sentiment);
		this.themeEntities = themeEntities;
	}
	
	public List<MontageTheme> getThemeEntities() {
		return themeEntities;
	}

	public void setThemeEntities(List<MontageTheme> themeEntities) {
		this.themeEntities = themeEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((themeEntities == null) ? 0 : themeEntities.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecificThemeIntent other = (SpecificThemeIntent) obj;
		if (themeEntities == null) {
			if (other.themeEntities != null)
				return false;
		} else if (!themeEntities.equals(other.themeEntities))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + ": " + themeEntities;
	}
}
