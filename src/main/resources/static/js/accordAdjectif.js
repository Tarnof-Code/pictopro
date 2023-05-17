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
                 mot = mot + "e"
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

