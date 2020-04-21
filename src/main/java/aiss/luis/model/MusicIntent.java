package aiss.luis.model;

import java.util.List;

public class MusicIntent extends Intent{
	protected List<String> author;
	protected MusicMood mood;
	protected MusicTempo tempo;
	protected MusicEnergy energy;
	protected Boolean danceable;
	
	public MusicIntent() {
		super();
		topIntent = IntentType.MusicDescription;
		mood = MusicMood.none;
		tempo = MusicTempo.none;
		energy = MusicEnergy.none;
		danceable = false;
	}

	public List<String> getAuthor() {
		return author;
	}

	public void setAuthor(List<String> author) {
		this.author = author;
	}

	public MusicMood getMood() {
		return mood;
	}

	public void setMood(MusicMood mood) {
		this.mood = mood;
	}

	public MusicTempo getTempo() {
		return tempo;
	}

	public void setTempo(MusicTempo tempo) {
		this.tempo = tempo;
	}

	public MusicEnergy getEnergy() {
		return energy;
	}

	public void setEnergy(MusicEnergy energy) {
		this.energy = energy;
	}

	public Boolean getDanceable() {
		return danceable;
	}

	public void setDanceable(Boolean danceable) {
		this.danceable = danceable;
	}
	
	
}
