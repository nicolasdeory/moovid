package aiss.model.luis.classes;

import java.util.List;

import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MusicEnergy;
import aiss.model.luis.enumerates.MusicMood;
import aiss.model.luis.enumerates.MusicTempo;
import aiss.model.luis.enumerates.Sentiment;

public class MusicIntent extends Intent{
	protected List<String> author;
	protected MusicMood mood;
	protected MusicTempo tempo;
	protected MusicEnergy energy;
	protected Boolean danceable; // Danceable is True or **NONE**, not true or false!
	// TODO: ADD GENRE
	public MusicIntent() {
		super(IntentType.MusicDescription);
		this.mood = MusicMood.none;
		this.tempo = MusicTempo.none;
		this.energy = MusicEnergy.none;
		this.danceable = false;
	}
	
	public MusicIntent(Sentiment sentiment, List<String> author,
	MusicMood mood, MusicTempo tempo, MusicEnergy energy, Boolean danceable) {
		super(IntentType.MusicDescription, sentiment);
		this.author = author;
		this.mood = mood;
		this.tempo = tempo;
		this.energy = energy;
		this.danceable = danceable;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((danceable == null) ? 0 : danceable.hashCode());
		result = prime * result + ((energy == null) ? 0 : energy.hashCode());
		result = prime * result + ((mood == null) ? 0 : mood.hashCode());
		result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
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
		MusicIntent other = (MusicIntent) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (danceable == null) {
			if (other.danceable != null)
				return false;
		} else if (!danceable.equals(other.danceable))
			return false;
		if (energy != other.energy)
			return false;
		if (mood != other.mood)
			return false;
		if (tempo != other.tempo)
			return false;
		return true;
	}
	
	
}
