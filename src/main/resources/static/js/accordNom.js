function accordNom(){

// Check des tags précédents pour l'accord
     var tagsPrecedents =  tableauTags[tableauTags.length - 1]


    if(tagsPrecedents != null && tagsPrecedents.includes("pluriel")){

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