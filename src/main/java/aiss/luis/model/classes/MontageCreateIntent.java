package aiss.luis.model.classes;

import java.time.LocalDate;
import java.util.List;

import aiss.luis.model.enumerates.MontageTheme;

public class MontageCreateIntent extends Intent{
	protected List<MontageTheme> themeEntities;
	protected LocalDate start;
	protected LocalDate end;
	
	public MontageCreateIntent() {
		super();
	}
}
