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

  function createUserMessage(message)
  {
    //if the message from the user isn't empty then run 
    if (message != "")
    {
      $("#chat-container").append(USER_MESSAGE_HTML.format(message));
    }
  }
  
  function createBotMessage(message)
  {
    if (message != "")
    {
      $("#chat-container").append(BOT_MESSAGE_HTML.format(message));
    }
  }

  function processUserMessage(message)
  {
    if (message != "")
    {
      createUserMessage(message);
      $.get("/chatquery?q="+message, function(resp)
      {
        console.log(resp);
        resp.messages.forEach((msg) =>
        {
          createBotMessage(msg);
        });
      });
    }
  }

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