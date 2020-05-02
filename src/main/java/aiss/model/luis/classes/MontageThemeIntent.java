package aiss.model.luis.classes;

import java.util.List;

import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MontageTheme;
import aiss.model.luis.enumerates.Sentiment;

public class MontageThemeIntent extends Intent{
	protected List<MontageTheme> themeEntities;
	
	public MontageThemeIntent() {
		super(IntentType.MontageThemeIntent);
	}

	public MontageThemeIntent(Sentiment sentiment, List<MontageTheme> themeEntities) {
		super(IntentType.MontageThemeIntent, sentiment);
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
		MontageThemeIntent other = (MontageThemeIntent) obj;
		if (themeEntities == null) {
			if (other.themeEntities != null)
				return false;
		} else if (!themeEntities.equals(other.themeEntities))
			return false;
		return true;
	}
	
	
}
