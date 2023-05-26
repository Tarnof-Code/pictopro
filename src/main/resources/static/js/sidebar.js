// Effet quand bouton est cliqué
$(document).ready(function() {
  $('.iconButton').click(function() {
    var button = $(this);
    button.addClass('active clicked');
    setTimeout(function() {
      button.removeClass('active clicked');
    }, 1000);
  });
});

// Fonction textToSpeech
function textToSpeech(messageToSpeech){
    if ('speechSynthesis' in window) {
      var message = new SpeechSynthesisUtterance();
      message.text = messageToSpeech;

		var voices = speechSynthesis.getVoices();
		// message.voice = voices[0];
		message.lang = "fr-FR"; // voix en français
		message.rate = 1;
		message.volume = 0.8;

      speechSynthesis.speak(message);
    }
}

// Lecture de la phrase
$("#lecturePhrase").click(function(){
    if(phrase == undefined || phrase == ""){
        phrase = "La phrase est vide";
    }
    textToSpeech(phrase);
});


// Fonction pour mettre en surbrillance
function motEnSurbrillance(phrase, mot,indexMotActuel) {
  let mots = phrase.split(" ");
  for (let i = 0; i < mots.length; i++) {
    if (mots[i] === mot && i == indexMotActuel) {
      mots[i] = '<span class="surbrillance">' + mot + '</span>';
      break; // Arrêter la boucle une fois le mot trouvé et mis en surbrillance
    }
  }
  let phraseMotSurbrillance = mots.join(" ");
  $("#contenuPhrase").html(phraseMotSurbrillance);
}


// Lecture mot à mot
var indexMotActuel = 0;
$("#lectureMotAmot").click(function(){
      if (phrase != undefined) {
        var phraseALire = phrase.split(" ");

        if(indexMotActuel < phraseALire.length){
            var motActuel = phraseALire[indexMotActuel];
            textToSpeech(motActuel);
            motEnSurbrillance(phrase,motActuel,indexMotActuel)
            indexMotActuel++;
        }
        if(indexMotActuel == phraseALire.length){
            indexMotActuel = 0;
        }
      }
});


// Mettre au futur
$("#mettreAuFutur").click(function(){
      if(temps == "passe"){
        temps = "present"
      } else {
        temps = "futur";
      }
      updateTableauMots();
});

// Mettre au passé
$("#mettreAuPasse").click(function(){
     if(temps =="futur"){
        temps = "present"
     } else {
        temps = "passe"
     }
      updateTableauMots();
});


// Supprimer tous les pictos
$('#supprimerPictos').click(function(){
console.log("COUCOU")
   $(".item2").each(function() {
   console.log("JSUIS LA")
     var empty = $("<li>", {"class": "empty"});
     $(this).replaceWith(empty);
   });
   updateDroppables();
   updateTableauMots();
});
