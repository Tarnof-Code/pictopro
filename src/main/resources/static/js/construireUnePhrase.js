var tags;
var mot;
var idMot;
var tableauMots;
var tableauId;
var tableauTags;
var tableauSingulierPluriel;
var tableauFemininMasculin;
var femininMasculinPrecedent;
var singulierPlurielPrecedent;
var phrase;
var temps = "present";


//Mettre à jour le tableau des mots
function updateTableauMots() {

    tableauMots = [];
    tableauId = [];
    tableauTags = [];
    tableauSingulierPluriel = [];
    tableauFemininMasculin = [];

    $(".target .item2").each(function(index) {
      mot = $(this).data('mot-nom');
      tags = $(this).data('mot-tags');
      idMot = $(this).data('mot-id');

      if (index === 0) {
        mot = mot.charAt(0).toUpperCase() + mot.slice(1);
      }

      singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];
      femininMasculinPrecedent = tableauFemininMasculin[tableauFemininMasculin.length - 1];

      if(tags.includes("pronom_ou_determinant") && tags.includes("'singulier'")){
            tableauSingulierPluriel.push("singulier")
      } else if(tags.includes("'pluriel'") || singulierPlurielPrecedent == "pluriel"){
         tableauSingulierPluriel.push("pluriel")
      } else {
         tableauSingulierPluriel.push("singulier")
      }

      if(tags.includes("'feminin'") || femininMasculinPrecedent == "feminin"){
         tableauFemininMasculin.push("feminin")
      } else if (tags.includes("'nom'") && tags.includes("'masculin'")){
         tableauFemininMasculin.push("masculin")
      }else {
         tableauFemininMasculin.push("masculin")
      }

        verifClasseGrammaticale();
        tableauMots.push(mot);
        tableauId.push(idMot);
        tableauTags.push(tags);
    });

    phrase = tableauMots.join(' ').replace(/'\s+/g, "'"); // Le replace supprime l'espace du join si le mot finit par une apostrophe
    $("#contenuPhrase").text(phrase);

    indexMotActuel = 0;
}



// Vérification de la nature du mot
function verifClasseGrammaticale(){
    if(tags.includes("'verbe'")){
        conjugaison(temps);

    } else if (tags.includes("'nom'")) {
        accordNom();
    } else if (tags.includes("'adjectif'")) {
        accordAdjectif();
    }
}

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



