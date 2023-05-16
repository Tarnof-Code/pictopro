function accordAdjectif(){

// Check du tableauSingulierPluriel pour l'accord en nombre
     var tagsMotMoinsUn =  tableauTags[tableauTags.length - 1]
     var tagsMotMoinsDeux =  tableauTags[tableauTags.length - 2]



    if(tagsMotMoinsUn != null && tagsMotMoinsUn.includes("feminin")){

        if(tags.includes("irregulier")){
            $.ajax({
              url: '/getIrregulier/' + idMot,
              method: 'GET',
              dataType: 'json',
              async : false,
              success: function(data) {
                var feminin = data.feminin
                mot = feminin
              },
              error: function(error) {
                console.error(error);
              }
            });
        } else {
            mot = mot + "e"
        }
    }

    if(tagsMotMoinsDeux != null && (tagsMotMoinsUn.includes("pluriel") || tagsMotMoinsDeux.includes("pluriel"))){

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