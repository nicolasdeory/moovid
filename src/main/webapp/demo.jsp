<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
    <title>Moovid</title>

    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="mystyle.css">
    <link rel="icon" type="image/png" href="images/Moovid logo color.png">
	<script
	  src="https://code.jquery.com/jquery-3.5.0.min.js"
	  integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
	  crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta name="google-signin-client_id"
        content="719553712189-o30q99ddl6293f6p5qrde7eak1a2u02b.apps.googleusercontent.com">
    <meta name="google-signin-client_id" content="719553712189-o30q99ddl6293f6p5qrde7eak1a2u02b.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
    <script src="buttonscript.js"></script>

</head>
<% String controller = "/PhotosList"; %>

<c:if test="${not empty mediaItemId}">
    <% controller = "/PhotosGet";%>
</c:if>

<body>
    <img class="bg" src="images/background.png" />

    <div class="col-container">
        <div class="col-yellow1">
            <div class="title-container">
                <div class="title-inside-yellow">API TEST</div>
                <div class="black-line"></div>
            </div>
            <div class="col-content middle">
                <div class="subtitle-for-yellow1">
                    Press the API button you desire to try
                </div>
                <a id="luisbutton" class="button3">LUIS</a>
                <a id="spotifybutton" class="button3">SPOTIFY</a>
                <a id="photosbutton" href="/PhotosList" class="button3">PHOTOS</a>
            </div>
        </div>

        <div class="col-yellow2">
            <div class="title-container">
                <div class="title-inside-yellow">RESPONSE</div>
                <div class="black-line"></div>
            </div>
            <c:choose>
    			<c:when test="${not empty cajademo}">
        			<div class="text-container">
                		<div class="text-yellow">
                    		<span id="result">
                    		<c:out value="${requestScope.cajademo}"/>
                    		</span>
                		</div>
            		</div>
    			</c:when>    
    			<c:otherwise>
        			<div class="text-container">
                		<div class="text-yellow">
                    		<span id="result">
                    		
                			</span>
                		</div>
            		</div> 
    			</c:otherwise>
			</c:choose>
        </div>

    </div>

    <div>

        <footer id="footer">Moovid &copy; 2020</footer>
    </div>

</body>

</html>