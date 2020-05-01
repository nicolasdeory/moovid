function luisAPI() {
    document.getElementById("result").innerHTML = "HAS HECHO CLICK EN EL BOTÓN DE LUIS";
  }

function spotifyAPI(){
    document.getElementById("result").innerHTML = "HAS HECHO CLICK EN EL BOTÓN DE SPOTIFY";
}

function photosAPI(){
    document.getElementById("result").innerHTML = requestScope.mediaItem.filename;

}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    document.getElementById("result").innerHTML = 'ID: ' + profile.getId() + 'Name: ' + profile.getName() ;
}