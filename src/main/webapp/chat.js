function newEntry() {
  //if the message from the user isn't empty then run 
  if (document.getElementById("chatbox").value != "" ) {
    lastUserMessage = document.getElementById("chatbox").value;
    document.getElementById("chatbox").value = "";

    //hasta aqui guardo el mensaje en variable y reseteo. 


    parentElement = document.getElementById("chat-container"); //el contenedor
    childElement = document.createElement('div').classList.add("chat-right"); // el hijo
    childElement2 = document.createElement('div').classList.add("sp");
    userMsgContainer = document.createElement('div').classList.add("msg").classList.add("msg-right");
    userMsg = document.createElement('p');
    
    // meter lastmsg en p

    appendMsg = userMsgContainer.appendChild(userMsg);
    appendContainter = childElement.appendChild(userMsgContainer);
    appendChildElement2 = childElement.appendChild(childElement2);
    appendChildElement = parentElement.appendChild(childElement);

    

    }

  }
}

//runs the keypress() function when a key is pressed
document.onkeypress = keyPress;
//if the key pressed is 'enter' runs the function newEntry()
function keyPress(e) {
  var x = e || window.event;
  var key = (x.keyCode || x.which);
  if (key == 13 || key == 3) {
    //runs this function when enter is pressed
    newEntry();
  }
}