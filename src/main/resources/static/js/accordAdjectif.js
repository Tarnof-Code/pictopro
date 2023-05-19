function accordAdjectif(){

// Check du tableauSingulierPluriel pour l'accord en nombre
     var femininPlurielPrecedent = tableauFemininMasculin[tableauFemininMasculin.length - 1]
     var singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1]

     //Si mot précédent est féminin
          if(femininPlurielPrecedent == "feminin"){

            if(tags.includes("irregulier")){
                $.ajax({
                  url: '/getIrregulier/' + idMot,
                  method: 'GET',
                  dataType: 'json',
                  async : false,
                  success: function(data) {
                    var feminin = data.feminin
                    mot = feminin
                    //Si le mot précédent est féminin et pluriel
                    if(singulierPlurielPrecedent == "pluriel"){
                        mot = mot + "s"
                    }
                  },
                  error: function(error) {
                    console.error(error);
                  }
                });
            } else {
                 if(!mot.endsWith("e")){
                    mot = mot + "e"
                 }

                 // Si le mot précédent est féminin régulier et pluriel
                 if (singulierPlurielPrecedent == "pluriel") {
                     mot = mot + "s";
                 }
            }
        }

    // Si mot précédent est pluriel mais pas féminin
      else if(singulierPlurielPrecedent == "pluriel"){

            if(tags.includes("irregulier")){
                $.ajax({
                  url: '/getIrregulier/' + idMot,
                  method: 'GET',
                  dataType: 'json',
                  async : false,
                  success: function(data) {
                    var pluriel = data.pluriel
                    mot = pluriel
                  },
                  error: function(error) {
                    console.error(error);
                  }
                });
            } else {
                mot = mot + "s"
            }
        }
}


// Mise à jour de l'adjectif si placé avant un nom féminin
function misAJourAdjectif(id,tagsPrecedents,tagsMoinsDeux){

    if(tagsPrecedents.includes("irregulier")){ //Précédent car fonction appelée sur accordNom
        $.ajax({
          url: '/getIrregulier/' + id,
          method: 'GET',
          dataType: 'json',
          async : false,
          success: function(data) {
            if( (tagsMoinsDeux != undefined && tagsMoinsDeux.includes("'singulier'")) ||
                 tagsMoinsDeux == undefined   ){
                    adjectifMisAJour = data.feminin
            } else {
                    adjectifMisAJour = data.feminin + "s"
            }

            if(tableauMots.length == 1){
                adjectifMisAJour = adjectifMisAJour.charAt(0).toUpperCase() + adjectifMisAJour.slice(1);
            }

            tableauMots[tableauMots.length - 1] = adjectifMisAJour
          },
          error: function(error) {
            console.error(error);
          }
        });

    } else {
         $.ajax({
               url: '/getMot/' + id,
               method: 'GET',
               dataType: 'json',
               async : false,
               success: function(data) {
                 if( (tagsMoinsDeux != undefined && tagsMoinsDeux.includes("'singulier'")) ||
                      tagsMoinsDeux == undefined   ) {
                        if(data.nom.endsWith("e")){
                            adjectifMisAJour = data.nom
                        } else {
                            adjectifMisAJour = data.nom + "e"
                        }
                 } else {
                        if(data.nom.endsWith("e")){
                            adjectifMisAJour = data.nom + "s"
                        } else {
                            adjectifMisAJour = data.nom + "es"
                        }

                 };

                   if(tableauMots.length == 1){
                      adjectifMisAJour = adjectifMisAJour.charAt(0).toUpperCase() + adjectifMisAJour.slice(1);
                   }

                   tableauMots[tableauMots.length - 1] = adjectifMisAJour;
               },
               error: function(error) {
                 console.error(error);
               }
         });
    }

}

