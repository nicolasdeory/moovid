$(document).ready(() => 
{
	// LUIS
	function luisAPI() {
		$.get( "/demo/luis")
		  .done(function(data) {
			$("#result").text(JSON.stringify(data));
		  })
		  .fail(function() {
		    $("#result").text("Error obteniendo la respuesta para la API de Spotify");
		  });
	  }

	function spotifyAPI(){
		$.get( "/demo/spotify")
			  .done(function(data) {
				$("#result").text(JSON.stringify(data));
			  })
			  .fail(function() {
			    $("#result").text("Error obteniendo la respuesta para la API de Spotify");
			  });
	}

	function photosAPI(){
	    document.getElementById("result").innerHTML = "HAS HECHO CLICK EN EL BOTÓN DE PHOTOS";

	}
	
	$("#spotifybutton").click(()=>spotifyAPI());
	$("#luisbutton").click(()=>luisAPI());
	
});

