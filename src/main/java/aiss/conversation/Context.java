package aiss.conversation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.enumerates.MontageTheme;

public class Context implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContextType contextType;
	private List<MontageTheme> themeEntities;
	private LocalDate start;
	private LocalDate end;
	private MusicIntent music;
	private boolean waitingForInput;
	String previousState = "";
	private boolean isLoggedIn;
	private String accessToken;
	
	
	private long timestamp;
	
	public Context()
	{
		this.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
		this.contextType = ContextType.None;
	}
	
	public String getPreviousState() {
		return previousState;
	}
	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}
	
	public ContextType getContextType() {
		return contextType;
	}
	
	public void setContextType(ContextType context) {
		this.contextType = context;
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
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean previousStateEquals(String prevState)
	{
		return this.previousState != null && this.previousState.equals(prevState);
	}

	
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "Context [contextType=" + contextType + ", themeEntities=" + themeEntities + ", start=" + start
				+ ", end=" + end + ", music=" + music + ", waitingForInput=" + waitingForInput + ", previousState="
				+ previousState + ", isLoggedIn=" + isLoggedIn + ", timestamp=" + timestamp + "]";
	}
	
	
}
