function accordNom(){

// Check des tags précédents pour l'accord
     var motPrecedent = tableauMots[tableauMots.length - 1]
     var derniersTags =  tableauTags[tableauTags.length - 1]
     var singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1]
     var femininPlurielPrecedent = tableauFemininMasculin[tableauFemininMasculin.length - 1]



      if(singulierPlurielPrecedent != null && singulierPlurielPrecedent == "pluriel"){

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