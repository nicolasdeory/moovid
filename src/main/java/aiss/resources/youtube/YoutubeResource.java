package aiss.resources.youtube;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioFormat;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;

public class YoutubeResource {
	
	private static final Logger log = Logger.getLogger(YoutubeResource.class.getName());
	private static final String PATH = "../../../../../temp/music";
	
	public static String downloadVideo(String id) throws YoutubeException, IOException {
		YoutubeDownloader downloader = new YoutubeDownloader(); 
		YoutubeVideo video = downloader.getVideo(id);

		List<AudioFormat> audios = video.audioFormats();
		log.log(Level.FINER, "Number of available audio downloads: " + audios.size());
		System.out.println("Formatos encontrados: " + audios.size());
		AudioFormat f;
		Comparator<AudioFormat> cmp = (x,y) -> x.bitrate().compareTo(y.bitrate());
		Predicate<AudioFormat> pred = x -> x.bitrate() >= 160000;
		if (audios.stream().filter(pred).findFirst().isPresent())
			f = audios.stream().filter(pred).min(cmp).get();
		else
			f = audios.stream().max(cmp).get();
		log.log(Level.FINER, "Chosen audio download with bitrate of: " + f.bitrate());
		File outputDir = new File("PATH");
		File file = video.download(f, outputDir);
		log.log(Level.INFO, "Succesfully downloaded song at " + PATH + " : " + video.details().title());
		return file.getName();
	}
	
	public static void convertMP3(String song) throws IllegalArgumentException, InputFormatException, EncoderException {
		Encoder encoder = new Encoder();
		File source = new File(PATH + song);
		File target = new File(PATH + "target.mp3");
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(128000);
		audio.setChannels(2);
		audio.setSamplingRate(44100);
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		encoder.encode(new MultimediaObject(source), target, attrs);
		log.log(Level.INFO, "Succesfully endoced song to .mp3: " + song);
	}
}
