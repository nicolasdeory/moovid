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

  var CLIENT_RESPONSES = {};

  function createUserMessage(message)
  {
    //if the message from the user isn't empty then run 
    if (message != "")
    {
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


  function sendMultipleBotMessages(messages)
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
        $("#chatbox").prop("disabled", false); // reenable chatbox
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
        sendMultipleBotMessages(resp.messages);
      })
      .fail(() =>
      {
        sendMultipleBotMessages(getRandomClientResponse("error"));
      });
    }
  }


  function updateScroll(){
    var element = document.getElementById("chat-container");
    element.scrollTop = element.scrollHeight;
}

  $.get("/chat/clientResponses.json", function(data)
  {
    CLIENT_RESPONSES = data;
    sendMultipleBotMessages(getRandomClientResponse("start-conversation"));
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

});