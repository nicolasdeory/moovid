$.ready(() => 
{
	// LUIS
	function luisAPI() {
	    document.getElementById("result").innerHTML = "HAS HECHO CLICK EN EL BOTÓN DE LUIS";
	  }

	function spotifyAPI(){
		$.get( "/demo/spotify")
			  .done(function(data) {
				$("#result").text(data);
			  })
			  .fail(function() {
			    $("#result").text("Error obteniendo la respuesta para la API de Spotify");
			  });
	}

	function photosAPI(){
	    document.getElementById("result").innerHTML = "HAS HECHO CLICK EN EL BOTÓN DE PHOTOS";

	}
	
});

