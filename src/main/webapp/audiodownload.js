const PROXY_URL = "https://cors-anywhere.herokuapp.com/";

function retrieveMP3Audio(videoId, doneCallback, progressCallback, errorCallback)
{
    // We take a list of audioUrls and pick the first one that works
    console.log("downloading audio");
    retrieveApiAudio(videoId, (url) => { return downloadVideoFromUrl(url, doneCallback, progressCallback, errorCallback) }, progressCallback, errorCallback);

}

function downloadVideoFromUrl(mp3url, doneCallback, progressCallback, errorCallback)
{
    console.log("downloading audio url");
    progressCallback("Descargando", 80);
    // Last download step
    fetch(PROXY_URL + "http:" + mp3url).then(r => 
    {
        console.log(r);
        return r.blob();
    }).then((blob) =>
    {
        console.log(blob);
        if (blob.size < 1000) // less than 1000 bytes is not really an audio file...
        {
            errorCallback("audio-dl-failed")
            return;
        }
        else
        {
            progressCallback("Descargando audio... Descarga completada", 100);
            console.log("audio loaded");
            doneCallback(blob);
        }
    }).catch(() =>
    {
        console.log("error downloading audio from url")
        errorCallback("audio-dl-failed-laststep")
    });
}

function retrieveApiAudio(videoId, doneCallback, progressCallback, errorCallback)
{
    var lastStepId = "";
    // First step
    fetch(`https://api.recordmp3.co/fetch?v=${videoId}&apikey=8C9U81ku5`).then((resp) => resp.json())
    .then(data =>
    {
        console.log(data);
        lastStepId = data.step_id;
        if (data.status == "timeout" || data.status == "ok")
        {
            if (data.step_id == "waiting_for_worker")
            {
                progressCallback("Esperando al servidor. Por favor, ten paciencia, esto puede tomar unos minutos.", 0);
            }
            else if (data.step_id == "in_queue")
            {
                progressCallback("En cola", 30);
            }
            else if (data.step_id == "fetching_information")
            {
                progressCallback("Obteniendo información", 50);
            }
            else if (data.step_id == "converting")
            {
                progressCallback("Convirtiendo", 60);
            }
            else if (data.step_id == "done" || data.url)
            {
                progressCallback("Conversión completada", 70);
                doneCallback(data.url);
            }
            if (data.step_id != "done")
            {
                setTimeout(() =>
                {
                    retrieveApiAudio(videoId, doneCallback, progressCallback, errorCallback);
                }, data.timeout * 1000) // Wait timeout before sending another request
            }
        }
        else
        {
            // error or redownload status
            if (data.status == "redownload")
            {
                console.error("error downloading audio. Last state was " + lastStepId + ". Status is redownload");
                errorCallback("audio-dl-failed-redownload");
            }
            else
            {
                console.error("error downloading audio. Last state was " + lastStepId + ". Err message is " + data.message);
                if (data.message == "This video is unavailable.")
                {
                    errorCallback("audio-dl-failed-unknown-video");
                }
                else
                {
                    errorCallback("audio-dl-failed-stepx");
                }
            }

        }
    }).catch((err) =>
    {
        console.log("Error step 1. " + err);
        errorCallback("audio-dl-failed-step1");
    });
}