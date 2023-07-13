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

// Chargement et choix de la voix
var voices;
if ('speechSynthesis' in window) {
    speechSynthesis.onvoiceschanged = function() {
        voices = window.speechSynthesis.getVoices();
    }
}

// Fonction textToSpeech
function textToSpeech(messageToSpeech){
    if ('speechSynthesis' in window) {
        var message = new SpeechSynthesisUtterance();
        //console.log(voices);
        if (navigator.userAgent.includes("Chrome")) {
            message.voice = voices[9];
        }
        message.text = messageToSpeech;
        message.lang = "fr-FR"; // voix en français
        message.rate = 1;
        message.volume = 0.6;

        speechSynthesis.speak(message);
    }
}

// Lecture de la phrase au présent
$("#lecturePhrase").click(function(){
    temps = "present";
    updateTableauMots();
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
      temps = "futur";
      updateTableauMots();
      textToSpeech(phrase);
});

// Mettre au passé
$("#mettreAuPasse").click(function(){
      temps = "passe";
      updateTableauMots();
      textToSpeech(phrase);
});

// Supprimer tous les pictos
$('#supprimerPictos').click(function(){
   $(".item2").each(function() {
     var empty = $("<li>", {"class": "empty"});
     $(this).replaceWith(empty);
   });
   negation = false;
   interrogation = false;
   updateDroppables();
   updateTableauMots();
});

// Mettre à la forme négative
$('#formeNegative').click(function(){
    negation = true;
    updateTableauMots();
    textToSpeech(phrase);
});

// Mettre à la forme affirmative
$('#formeAffirmative').click(function(){
    negation = false;
    updateTableauMots();
    textToSpeech(phrase);
});

// Transformer en question
$('#formeInterrogative').click(function(){
    if(interrogation == true) {
        interrogation = false;
    } else {
        interrogation =true
    }
    updateTableauMots();
    textToSpeech(phrase);
});

// Afficher la navbar
$('#sideBarBurger').click(function(){
    if($("#hidden-navbar").is(":visible")){
        $("#hidden-navbar").fadeOut(1000);
        $("#sideBarBurger").removeClass("bi-arrows-fullscreen").addClass("bi bi-list-ul");
    } else {
         $("#hidden-navbar").fadeIn(1500);
         $("#sideBarBurger").removeClass("bi bi-list-ul").addClass("bi-arrows-fullscreen");
    }
});
