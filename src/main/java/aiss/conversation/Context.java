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
	public ContextType getContextType() {
		return context;
	}
	public void setContext(ContextType context) {
		this.context = context;
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
	public MusicIntent getMusic() {
		return music;
	}
	public void setMusic(MusicIntent music) {
		this.music = music;
	}
	public boolean isWaitingForInput() {
		return waitingForInput;
	}
	public void setWaitingForInput(boolean waitingForInput) {
		this.waitingForInput = waitingForInput;
	}
	
	
}
