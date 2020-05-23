const USER_MESSAGE_HTML = 
`
<div class="chat-right">
  <div class="sp"></div>
  <div class="msg msg-right">
    <p>{0}</p>
  </div>
</div>
`;

const BOT_MESSAGE_HTML = 
`
<div class="chat-left">
  <div class="msg">
    <p>{0}</p>
  </div>
</div>
`;

const LOADING_MESSAGE_HTML = 
`
<div class="chat-left" id="loading-container">
  <div class="msg loading-montage">
      <p class="info-montage" id="loading-message"></p>
      <div class="loading-container">
          <div class="loading-bar" id="loading-bar"></div>
      </div>
  </div>
</div>
`;

const VIDEO_MESSAGE_HTML = 
`
<div class="chat-left">
  <div class="msg">
    <video class="video-solution" controls><source type="video/mp4" src="{0}"> </video>
  </div>
</div>
`;


if (!String.prototype.format) {
  String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) { 
      return typeof args[number] != 'undefined'
        ? args[number]
        : match
      ;
    });
  };
}

$(document).ready(() =>
{


  $("#info-button").click(() =>
  {
    if($("#help-box").is(':hidden')){

    $("#help-box").show();
  }else{
    $("#help-box").hide();
  }


  });
  

  function logged(){
    const currentUrl = window.location.href;
      if (currentUrl.indexOf("login=1") >= 0)
      {
        return true;
      }
      else
      {
        return false;
      }
  }

  var CLIENT_RESPONSES = {};

  function createUserMessage(message)
  {
    //if the message from the user isn't empty then run 
    if (message != "")
    {
      $("#help-box").hide();
      $("#chat-container").append(USER_MESSAGE_HTML.format(message));
      updateScroll();
    }
  }
  
  function createBotMessage(message)
  {
    if (message != "")
    {
      $("#chat-container").append(BOT_MESSAGE_HTML.format(message));
      updateScroll();
    }
  }


  function sendMultipleBotMessages(messages, shouldEnableLoading) // when shouldEnableLoading is true, when messages are done being sent, loading goes visible
  {
    let i = 0;
    var interval = setInterval(function ()
    {
      var msg = messages[i];
      createBotMessage(msg);
      i++;
      if (i >= messages.length)
      {
        clearInterval(interval);
        if (shouldEnableLoading)
        {
          $("#loading-container").show();
          $('#loading-container').appendTo('#chat-container');
          updateScroll();
          // chatbox doesn't get reenabled until montage is done
        } else
        {
          $("#chatbox").prop("disabled", false); // reenable chatbox
        }
      }
    }, 700);
  }

  function getRandomClientResponse(key)
  {
    return CLIENT_RESPONSES[key][Math.floor(Math.random() * CLIENT_RESPONSES[key].length)];
  }

  function processUserMessage(message)
  {
    $("#chatbox").prop("disabled", true);
    if (message != "")
    {
      createUserMessage(message);
      var chatQuery = "/chatquery";
      const currentUrl = window.location.href;
      if (currentUrl.indexOf("login=1") >= 0)
      {
        chatQuery += "?login=1&";
      }
      else
      {
        chatQuery += "?";
      }
      $.get(chatQuery + "q="+message, function(resp)
      {
        console.log(resp);
        
        if (resp.responseType == "VideoGeneration")
        {
          sendMultipleBotMessages(resp.messages, true);
          processMontage(resp.jobId);
        } else
        {
          sendMultipleBotMessages(resp.messages);
        }
      })
      .fail(() =>
      {
        sendMultipleBotMessages(getRandomClientResponse("error"));
      });
    }
  }

  function processMontage(jobId)
  {
    $("#chat-container").append(LOADING_MESSAGE_HTML); // Add loading message with id #loading-container
    $("#loading-container").hide();
    montageProgress("Buscando imágenes...", 0);

    $.get(`/getjob?id=${jobId}`, function(data)
    {
      // TODO: get bpm
      console.log("received job data");
      console.log(data);
      const imgUrls = data.photoUrls;
      imgUrls = imgUrls.reverse();
      if (imgUrls.length > 100) // MAX 100 photos
      {
        imgUrls.splice(imgUrls.length-(imgUrls.length-100), imgUrls.length);
      }
      const audioUrl = data.musicUrl;

      
      montageProgress("Descargando imágenes...", 5);
      
      makeMontage(imgUrls, audioUrl, montageDone, montageProgress);
    })
  }

  function montageDone(blob)
  {
    var src = window.URL.createObjectURL(blob);
    $("#loading-container").remove();
    $("#chat-container").append(VIDEO_MESSAGE_HTML.format(src));
    sendMultipleBotMessages(getRandomClientResponse("montage-done"));
    setTimeout(() => {
      createBotMessage(`Puedes descargar el vídeo <a href="${src}" download="moovid.mp4">aquí</a>.`)
    },1000);
    
    $("#chatbox").prop("disabled", false); // reenable chatbox
    // var a = document.createElement('a');
   // a.download = "moovid.mp4";
    
   //  a.href = src;
    // a.textContent = 'Click here to download ' + "moovid.mp4" + "!";
   //  $("body").append(a);
  }

  function montageProgress(message, progressPct)
  {
    $("#loading-bar").css("width", progressPct+"%");
    $("#loading-message").text(message);
  }

  function updateScroll(){
    var element = document.getElementById("chat-container");
    element.scrollTop = element.scrollHeight;
  }

  $.get("/chat/clientResponses.json", function(data)
  {
    CLIENT_RESPONSES = data;
    if (logged())
    {
      sendMultipleBotMessages(getRandomClientResponse("start-conversation-after-login"));
    }
    else
    {
      sendMultipleBotMessages(getRandomClientResponse("start-conversation"));
    }
    
  }).fail(() =>
  {
    createBotMessage("Lo siento, ha ocurrido un error que no me esperaba. Prueba a volver a cargar la página.")
  });

  //runs the keypress() function when a key is pressed
  document.onkeypress = keyPress;
  //if the key pressed is 'enter' runs the function newEntry()
  function keyPress(e)
  {
    var x = e || window.event;
    var key = (x.keyCode || x.which);
    if (key == 13 || key == 3)
    {
      //runs this function when enter is pressed
      processUserMessage($("#chatbox").val());
      $("#chatbox").val("");
    }
  }

  $("#send-button").click(() =>
  {
    processUserMessage($("#chatbox").val());
    $("#chatbox").val("");
  });

  if (logged())
  {
    $("#photosbutton").hide();
    $("#subtitle-for-yellow1").text("¡Bienvenido a Moovid! Si tienes alguna pregunta, tan sólo pide ayuda.");
  }

});