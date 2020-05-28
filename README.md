# Moovid
A mashup webapp integrating Google Photos, Spotify and Cognitive Services LUIS to create customized photo collage videos.

## Usage
1. Go to https://moovid-271019.appspot.com/. At the moment the chatbot only works with spanish input. Grant Moovid access to your Google Photos library by logging in. You might see a security warning, because the app is not yet verified. You can click on "Advanced" -> "Allow (unsafe)".
2. Ask it to make a montage for you (_"Hazme un montaje"_). 
You can specify details such as a date range:
- _"Hazme un montaje con fotos de las últimas 3 semanas"_
- _"Hazme un montaje del año pasado"_
- _"Hazme un montaje con fotos de julio de 2019"_
- _"Hazme un montaje con fotos entre el 3 de mayo de 2019 y el 12 de mayo de 2019"_

You can also specify the montage theme:
- _"Quiero un montaje con fotos de mis amigos"_
- _"Quiero que me saques fotos de paisajes"_
3. If you didn't specify a theme, the bot will ask you if you want a particular one. You can ask it to decide for you, if you wish.
- _"Decide por mí"_
- _"Me da igual"_
4. The bot asks you what kind of music you'd like to hear in the montage. You can specify from simple parameters such as the song genre or artist, to more complex one such as the song "danceability", mood, energy or BPM. You can also ask it to decide for you, and it will pick a popular song.
- _"Quiero una canción de EDM de Zedd"_ (Artist: Zedd, Genre: EDM)
- _"Quiero una canción triste de Don Diablo y Zedd"_ (Artist: Don Diablo, Zedd; Mood: Sad)
- _"Ponme un temazo de reggaeton de Bad Bunny"_ (Artist: Bad Bunny, Genre: Reggaeton, Energy: High)
- _"Una canción de anime rápida"_ (Genre: Anime, BPM: Fast)
- _"Escoge por mí"_

If you want a particular song, you can directly paste the YouTube link, or include it in the message.
- _"Quiero esta canción https://www.youtube.com/watch?v=xRNnxptNp2g"_
5. Moovid now has enough information to make a montage. A progress bar will appear, and the app will download the photos and song audio on your device. Once the download is done, Moovid invokes [FFMPEG.js](https://github.com/Kagami/ffmpeg.js/) to generate a video. Once it's done, the video will be displayed and a message with a download link will appear.

## Performance considerations
Moovid does the montage client-side using FFMPEG.js. This delegates the most resource-heavy operation (the video generation itself) to the client. The Javascript Emscripten port of the FFMPEG project isn't running on WebAssembly, so it won't be able to take advantage of multi-threading and other browser optimizations.

The client-side portion of the app works on mobile, although it might take longer than usual if the mobile device is not powerful enough. Running Moovid on an iPhone 8 roughly has the same video encoding speed as a desktop with an i7 9700K.

## Building and running locally
You can build the Maven project with a Java IDE such as Eclipse. Maven should resolve all the dependencies por you.

If you run the app locally, make sure you launch it with one of these profiles:
- StartDevServer.launch (Launchs the app on localhost:8090)
- StopDevServer.launch (Stops the local server)
- DeployApplication.launch (Deploys the app to AppEngine. Needs a live AppEngine project to deploy to)

Keep in mind that for the app to work properly you will need a Spotify API Key and a YouTube API Key.
Place them under `/src/main/resources/keys/SpotifyKey.txt` and `/src/main/resources/keys/YoutubeKey.txt`.

The API key for LUIS is hardcoded, because you would need to create your own model and train it otherwise. If you wish to make changes to the model, you will have to roll your own LUIS project. 

## About
This mashup was made as a class project for the University of Seville, tutorized by Javier Troya Castilla.

### Main contributors
- Daniel Caro Olmedo
- Ignacio Navarro Blázquez
- Antonio González Gómez
- Nicolás de Ory Carmona
