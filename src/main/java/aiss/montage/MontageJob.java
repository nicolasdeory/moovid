package aiss.montage;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import aiss.model.luis.classes.MusicIntent;
import aiss.model.luis.enumerates.MontageTheme;

public class MontageJob 
{

	private UUID uuid;
	private List<MontageTheme> themes;
	private LocalDate start;
	private LocalDate end;
	private MusicIntent musicDesciption;
	
	public static MontageJob of(List<MontageTheme> themes, MusicIntent musicDescription, LocalDate start, LocalDate end)
	{
		return new MontageJob(themes, musicDescription, start, end);
	}
	
	private MontageJob(List<MontageTheme> themes, MusicIntent musicDesciption, LocalDate start, LocalDate end) {
		super();
		this.themes = themes;
		this.musicDesciption = musicDesciption;
		this.start = start;
		this.end = end;
		this.uuid = UUID.randomUUID();
	}

	public List<MontageTheme> getThemes() {
		return themes;
	}

	public void setThemes(List<MontageTheme> themes) {
		this.themes = themes;
	}

	public MusicIntent getMusicDesciption() {
		return musicDesciption;
	}

	public void setMusicDesciption(MusicIntent musicDesciption) {
		this.musicDesciption = musicDesciption;
	}

	public UUID getUuid() {
		return uuid;
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
	
	
	
	
	
	
	
	
}
