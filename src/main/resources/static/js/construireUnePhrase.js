var tags;
var mot;
var idMot;
var tableauMots;
var tableauTags;
var tableauSingulierPluriel;
var tableauFemininMasculin;


function updateTableauMots() {

    tableauMots = [];
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

      tableauTags.push(tags);

      var singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];
      var femininMasculinPrecedent = tableauFemininMasculin[tableauFemininMasculin.length - 1];
      var derniersTags = tableauTags[tableauTags.length - 1]
      var tagsPrecedents = tableauTags[tableauTags.length - 2]

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

console.log("avant = "+tableauFemininMasculin)
console.log("tags = "+tags)
console.log("derniersTags = "+derniersTags)

         if (tags.includes("'nom'") && tags.includes("'feminin'")) {
              if(tagsPrecedents !=null &&
                 tagsPrecedents.includes("'adjectif'") &&
                 tableauFemininMasculin.length > 1){

                  tableauFemininMasculin[tableauFemininMasculin.length - 3] = "feminin";

console.log("après "+tableauFemininMasculin)

              }
          }


        verifClasseGrammaticale();
        tableauMots.push(mot);

    });

    var phrase = tableauMots.join(' ');

    $("#contenuPhrase").text(phrase);
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



