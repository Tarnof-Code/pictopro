var tableauMots;
var tags;
var mot;
var idMot;


function updateTableauMots() {

    tableauMots = [];

    $(".target .item2").each(function(index) {
      mot = $(this).data('mot-nom');
      tags = $(this).data('mot-tags');
      idMot = $(this).data('mot-id');

      if (index === 0) {
        mot = mot.charAt(0).toUpperCase() + mot.slice(1);
      }

      verifClasseGrammaticale();


      tableauMots.push(mot);

    });

    var phrase = tableauMots.join(' ');

    $("#contenuPhrase").text(phrase);
}

// Vérification de la nature du mot
function verifClasseGrammaticale(){
    if(tags.includes("verbe")){
        conjugaisonPresent();
    }

}



