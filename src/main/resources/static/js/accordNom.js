function accordNom(){

// Check des tags précédents pour l'accord
     var tagsPrecedents =  tableauTags[tableauTags.length - 1]
     var tagsMoinsDeux = tableauTags[tableauTags.length - 2]
     var idMotPrecedent = tableauId[tableauId.length - 1]
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

      if (tags.includes("'feminin'")) {
        if(tagsPrecedents != undefined && tagsPrecedents.includes("'adjectif'")){
                var adjectifMisAJour;
                misAJourAdjectif(idMotPrecedent,tagsPrecedents,tagsMoinsDeux)

        }
      }

}