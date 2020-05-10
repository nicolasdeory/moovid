package aiss.conversation;

import java.time.LocalDate;
import java.util.List;

import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.enumerates.MontageTheme;

public class Context {
	private ContextType context;
	private List<MontageTheme> themeEntities;
	private LocalDate start;
	private LocalDate end;
	private MusicIntent music;
	private boolean waitingForInput;
}
