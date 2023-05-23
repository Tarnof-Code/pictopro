var tags;
var mot;
var idMot;
var tableauMots;
var tableauId;
var tableauTags;
var tableauSingulierPluriel;
var tableauFemininMasculin;
var phrase;

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

    //  verifClasseGrammaticale();
    //  tableauMots.push(mot);

   //  tableauTags.push(tags);

      var singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];
      var femininMasculinPrecedent = tableauFemininMasculin[tableauFemininMasculin.length - 1];

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
        conjugaisonPresent();

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

// Fonction mise en surbrillance
function motEnSurbrillance(phrase,mot){
    let phraseMotSurbrillance = phrase.replace(mot, '<span class="surbrillance">' + mot + '</span>')
    $("#contenuPhrase").html(phraseMotSurbrillance);
}



// Lecture de la phrase
$("#lecturePhrase").click(function(){
    if(phrase == undefined || phrase == ""){
        phrase = "La phrase est vide";
    }
    textToSpeech(phrase);
});


// Lecture mot à mot
var indexMotActuel = 0;

$("#lectureMotAmot").click(function(){
      if (phrase != undefined) {
        var phraseALire = phrase.split(" ");
        if(indexMotActuel < phraseALire.length){
            var motActuel = phraseALire[indexMotActuel];
            textToSpeech(motActuel);
            motEnSurbrillance(phrase,motActuel)
            indexMotActuel++;
        }
        if(indexMotActuel == phraseALire.length){
            indexMotActuel = 0;
        }
      }
});





