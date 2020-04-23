package aiss.luis.model.classes;

import java.time.LocalDate;
import java.util.List;

import aiss.luis.model.enumerates.IntentType;
import aiss.luis.model.enumerates.MontageTheme;
import aiss.luis.model.enumerates.Sentiment;

public class MontageCreateIntent extends Intent{
	protected List<MontageTheme> themeEntities;
	protected LocalDate start;
	protected LocalDate end;
	
	public MontageCreateIntent() {
		super(IntentType.CreateMontage);
	}

	public MontageCreateIntent(Sentiment sentiment, List<MontageTheme> themeEntities,
			LocalDate start, LocalDate end) {
		super(IntentType.CreateMontage, sentiment);
		this.themeEntities = themeEntities;
		this.start = start;
		this.end = end;
	}
	
	public List<MontageTheme> getThemeEntities() {
		return themeEntities;
	}

	public void setThemeEntities(List<MontageTheme> themeEntities) {
		this.themeEntities = themeEntities;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		MontageCreateIntent other = (MontageCreateIntent) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (themeEntities == null) {
			if (other.themeEntities != null)
				return false;
		} else if (!themeEntities.equals(other.themeEntities))
			return false;
		return true;
	}
	
	
}
