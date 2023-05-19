function conjugaisonPresent(){

// Check du mot précédent pour la conjugaison
     var motPrecedent =  tableauMots[tableauMots.length - 1]
     if (motPrecedent != null){
        motPrecedent = motPrecedent.toLowerCase()
     }

// Check mot précédent si c'est un nom pour conjugaison
var tagsPrecedents =  tableauTags[tableauTags.length - 1]
var singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];

// Si verbe pronominal

    if(mot.startsWith("se ")){
        // Enlever "se"
        var resteDuMot = mot.substring(2);

        switch(motPrecedent){
            case 'je': mot = "me" + resteDuMot
                break;
            case 'tu': mot = "te" + resteDuMot
            case 'il':
            case 'elle':
            case 'ils':
            case 'elles': mot = "se" + resteDuMot
                break;
            case 'nous': mot = "nous" + resteDuMot
                break;
            case 'vous': mot = "vous" + resteDuMot
                break;
            default : mot = mot;
        }
    }

    if(mot.startsWith("s'")){
            // Enlever "s'"
            var resteDuMot = mot.substring(2);

            switch(motPrecedent){
                case 'je': mot = "m'" + resteDuMot
                    break;
                case 'tu': mot = "t'" + resteDuMot
                case 'il':
                case 'elle':
                case 'ils':
                case 'elles': mot = "s'" + resteDuMot
                    break;
                case 'nous': mot = "nous " + resteDuMot
                    break;
                case 'vous': mot = "vous " + resteDuMot
                    break;
                default : mot = mot;
            }
        }



//Radical des verbes du premier et deuxième groupe
    if(!tags.includes('irregulier')){
        var radical = mot.substring(0, mot.length - 2)
    }



// Conjugaison pour les verbes du 1er groupe
     if(tags.includes("premier_groupe")){

        // Fonction de conjugaison
        function conjugaisonPremierGroupe(){

        if(tagsPrecedents != undefined && tagsPrecedents.includes("'nom'")) {
            if(singulierPlurielPrecedent == "singulier"){
                mot = radical + "e"
            } else {
                mot = radical + "ent"
            }
        }

             switch(motPrecedent){
                case 'je': mot = radical + "e";
                    break;
                case 'tu': mot = radical + "es";
                    break;
                case 'il':
                case 'elle': mot = radical + "e";
                    break;
                case 'nous':
                    if (radical.endsWith("g")) {
                        mot = radical + "eons";
                    } else if (radical.endsWith("c")) {
                        mot = radical.replace(/c$/, "ç") + "ons";
                    } else {
                        mot = radical + "ons";
                    }
                    break;
                case 'vous': mot = radical + "ez";
                    break;
                case 'ils':
                case 'elles': mot = radical + "ent";
                    break;
                default : mot = mot;
            }
        };

            // Si d'autres mots après le verbe
            var listeMots = mot.split(' ');

            if(listeMots.length > 2){
                for (var i = 0; i < listeMots.length; i++) {
                    var motDeLaListe = listeMots[i];
                    if (motDeLaListe.endsWith('er')) {
                       var verbePrincipal = motDeLaListe;
                       var resteDeLaPhrase = listeMots.slice(i + 1).join(' ');
                       if(i > 0){
                           verbePrincipal = listeMots[i-1] + ' ' + verbePrincipal;
                       }
                    }
                  }
                radical = verbePrincipal.substring(0, verbePrincipal.length - 2)

                conjugaisonPremierGroupe()

                if (motPrecedent == 'je' || motPrecedent == 'tu' || motPrecedent == 'il' ||
                    motPrecedent == 'elle' || motPrecedent == 'nous' || motPrecedent == 'vous' ||
                    motPrecedent == 'ils' || motPrecedent == 'elles') {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

                if(tagsPrecedents != undefined && tagsPrecedents.includes("'nom'")) {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

            } else {

            // Si pas d'autres mots après le verbe
                    conjugaisonPremierGroupe()
            }
     };




// Conjugaison pour les verbes du 2e groupe
      if(tags.includes("deuxieme_groupe")){

        //Fonction de conjugaison
            function conjugaisonDeuxiemeGroupe(){

                if(tagsPrecedents != undefined && tagsPrecedents.includes("'nom'")) {
                    if(singulierPlurielPrecedent == "singulier"){
                        mot = radical + "it"
                    } else {
                        mot = radical + "issent"
                    }
                }

                switch(motPrecedent){
                     case 'je': mot = radical + "is";
                         break;
                     case 'tu': mot = radical + "is";
                         break;
                     case 'il':
                     case 'elle': mot = radical + "it";
                         break;
                     case 'nous': mot = radical + "issons";
                         break;
                     case 'vous': mot = radical + "issez";
                         break;
                     case 'ils':
                     case 'elles': mot = radical + "issent";
                         break;
                     default : mot = mot;
                 }
            }

        // Si d'autres mots après le verbe
            var listeMots = mot.split(' ');

            if(listeMots.length > 2){
                for (var i = 0; i < listeMots.length; i++) {
                    var motDeLaListe = listeMots[i];
                    if (motDeLaListe.endsWith('ir')) {
                       var verbePrincipal = motDeLaListe;
                       var resteDeLaPhrase = listeMots.slice(i + 1).join(' ');
                       if(i > 0){
                           verbePrincipal = listeMots[i-1] + ' ' + verbePrincipal;
                       }
                    }
                  }
                radical = verbePrincipal.substring(0, verbePrincipal.length - 2)

                conjugaisonDeuxiemeGroupe()

                if (motPrecedent == 'je' || motPrecedent == 'tu' || motPrecedent == 'il' ||
                    motPrecedent == 'elle' || motPrecedent == 'nous' || motPrecedent == 'vous' ||
                    motPrecedent == 'ils' || motPrecedent == 'elles') {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

                if(tagsPrecedents != undefined && tagsPrecedents.includes("'nom'")) {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

            } else {

            // Si pas d'autres mots après le verbe
                    conjugaisonDeuxiemeGroupe()
            }

      };




      // Conjugaison pour les verbes du 3e groupe et verbes irréguliers
      if(tags.includes("irregulier")){

            $.ajax({
              url: '/getConjugaisonsIrregulier/' + idMot,
              method: 'GET',
              dataType: 'json',
              async : false,
              success: function(data) {

                var present = data[0]

                if(tagsPrecedents != undefined && tagsPrecedents.includes("'nom'")) {
                    if(singulierPlurielPrecedent == "singulier"){
                        mot = present.troisiemePersSing
                    } else {
                        mot = present.troisiemePersPluriel;
                    }
                }

                switch(motPrecedent){
                       case 'je': mot = present.premierePersSing;
                           break;
                       case 'tu': mot = present.deuxiemePersSing;
                           break;
                       case 'il':
                       case 'elle': mot = present.troisiemePersSing;
                           break;
                       case 'nous': mot = present.premierePersPluriel;
                           break;
                       case 'vous': mot = present.deuxiemePersPluriel;
                           break;
                       case 'ils':
                       case 'elles': mot = present.troisiemePersPluriel;
                           break;
                       default : mot = mot;
               }

              },
              error: function(error) {
                console.error(error);
              }
            });

       };

      if(motPrecedent == 'je' && mot[0] == "a" ||
         motPrecedent == 'je' && mot[0] == "e" ||
         motPrecedent == 'je' && mot[0] == "é" ||
         motPrecedent == 'je' && mot[0] == "i" ||
         motPrecedent == 'je' && mot[0] == "o" ||
         motPrecedent == 'je' && mot[0] == "u" ||
         motPrecedent == 'je' && mot[0] == "y") {
            if(tableauMots.length == 1){
                tableauMots[tableauMots.length - 1] = "J'"
            } else {
                tableauMots[tableauMots.length - 1] = "j'"
            }
         }
}