package aiss.model.luis.classes;

import java.util.ArrayList;
import java.util.List;

import aiss.model.luis.enumerates.IntentType;
import aiss.model.luis.enumerates.MusicAcoustic;
import aiss.model.luis.enumerates.MusicDanceable;
import aiss.model.luis.enumerates.MusicEnergy;
import aiss.model.luis.enumerates.MusicMood;
import aiss.model.luis.enumerates.MusicTempo;
import aiss.model.luis.enumerates.Sentiment;

public class MusicIntent extends Intent{
	protected List<String> author;
	protected List<String> genre;
	protected MusicMood mood;
	protected MusicTempo tempo;
	protected MusicEnergy energy;
	protected MusicDanceable danceable;
	protected MusicAcoustic acoustic;
	
	public MusicIntent() {
		super(IntentType.MusicDescription);
		this.author = new ArrayList<String>();
		this.genre = new ArrayList<String>();
		this.mood = MusicMood.none;
		this.tempo = MusicTempo.none;
		this.energy = MusicEnergy.none;
		this.danceable = MusicDanceable.none;
		this.acoustic = MusicAcoustic.none;
	}
	
	public MusicIntent(Sentiment sentiment, List<String> author, List<String> genre,
			MusicMood mood, MusicTempo tempo, MusicEnergy energy,
			MusicDanceable danceable, MusicAcoustic acoustic) {
		super(IntentType.MusicDescription, sentiment);
		this.author = author;
		this.genre = genre;
		this.mood = mood;
		this.tempo = tempo;
		this.energy = energy;
		this.danceable = danceable;
		this.acoustic = acoustic;
	}

	public List<String> getAuthor() {
		return author;
	}

	public List<String> getGenre() {
		return genre;
	}

	public MusicMood getMood() {
		return mood;
	}

	public MusicTempo getTempo() {
		return tempo;
	}

	public MusicEnergy getEnergy() {
		return energy;
	}

	public MusicDanceable getDanceable() {
		return danceable;
	}

	public MusicAcoustic getAcoustic() {
		return acoustic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acoustic == null) ? 0 : acoustic.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((danceable == null) ? 0 : danceable.hashCode());
		result = prime * result + ((energy == null) ? 0 : energy.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
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
		if (acoustic != other.acoustic)
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (danceable != other.danceable)
			return false;
		if (energy != other.energy)
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (mood != other.mood)
			return false;
		if (tempo != other.tempo)
			return false;
		return true;
	}

	
	
	
}
