function conjugaisonPresent(){

// Conjugaison présent pour les verbes du 1er groupe
     if(tags.includes("premier_groupe")){

        // Fonction de conjugaison
        function presentPremierGroupe(){

        if(tagsPrecedents != undefined &&
            (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ){
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

                presentPremierGroupe()

                if (motPrecedent == 'je' || motPrecedent == 'tu' || motPrecedent == 'il' ||
                    motPrecedent == 'elle' || motPrecedent == 'nous' || motPrecedent == 'vous' ||
                    motPrecedent == 'ils' || motPrecedent == 'elles') {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

                if(tagsPrecedents != undefined &&
                    (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ) {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

            } else {

            // Si pas d'autres mots après le verbe
                    presentPremierGroupe()
            }
     };




// Conjugaison pour les verbes du 2e groupe
      if(tags.includes("deuxieme_groupe")){

        //Fonction de conjugaison
            function presentDeuxiemeGroupe(){

                if(tagsPrecedents != undefined &&
                    (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ) {
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

                presentDeuxiemeGroupe()

                if (motPrecedent == 'je' || motPrecedent == 'tu' || motPrecedent == 'il' ||
                    motPrecedent == 'elle' || motPrecedent == 'nous' || motPrecedent == 'vous' ||
                    motPrecedent == 'ils' || motPrecedent == 'elles') {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

                if(tagsPrecedents != undefined &&
                    (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ) {
                    mot = mot + ' ' + resteDeLaPhrase;
                }

            } else {

            // Si pas d'autres mots après le verbe
                    presentDeuxiemeGroupe()
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

                if(tagsPrecedents != undefined &&
                    (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ) {
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
}