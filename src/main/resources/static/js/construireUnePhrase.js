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





