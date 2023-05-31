var tags;
var mot;
var idMot;
var tableauMots;
var tableauId;
var tableauTags;
var tagsPrecedents;
var tagsMoinsDeux;
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

      // Si premier mot, commence par une majuscule
      if (index === 0) {
        mot = mot.charAt(0).toUpperCase() + mot.slice(1);
      }

      tagsPrecedents  =  tableauTags[tableauTags.length - 1]
      singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];
      femininMasculinPrecedent = tableauFemininMasculin[tableauFemininMasculin.length - 1];

      if(tags.includes("pronom_ou_determinant") && tags.includes("'singulier'")){
            tableauSingulierPluriel.push("singulier")
      } else if(tags.includes("'pluriel'") || (singulierPlurielPrecedent == "pluriel" && !tagsPrecedents.includes("'verbe'"))){
         tableauSingulierPluriel.push("pluriel")
      } else {
         tableauSingulierPluriel.push("singulier")
      }

      tagsMoinsDeux =  tableauTags[tableauTags.length - 2];
      motPrecedent = tableauMots[tableauMots.length - 1];

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

        if(tableauMots.length > 2){
              if(tags.includes("'nom'") && tagsMoinsDeux.includes("'nom'") && motPrecedent == "et") {
                 tableauSingulierPluriel.push("pluriel")
              }
              if(!tags.includes("'pluriel'") && tagsMoinsDeux.includes("'verbe'") && motPrecedent == "et") {
                 tableauSingulierPluriel.push("singulier")
              }
        }

    });

    phrase = tableauMots.join(' ').replace(/'\s+/g, "'"); // Le "replace" supprime l'espace du join si le mot finit par une apostrophe
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





