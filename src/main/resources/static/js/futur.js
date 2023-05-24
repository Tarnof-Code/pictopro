function conjugaisonFutur(){

// Conjugaison futur pour les verbes du 1er et 2e groupe
     if(tags.includes("premier_groupe") || tags.includes("deuxieme_groupe")){

        // Fonction de conjugaison
        function futur(){

        if(tagsPrecedents != undefined &&
            (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ){
            if(singulierPlurielPrecedent == "singulier"){
                mot = mot + "a";
            } else {
                mot = mot + "ont";
            }
        }

             switch(motPrecedent){
                case 'je': mot = mot + "ai";
                    break;
                case 'tu': mot = mot + "as";
                    break;
                case 'il':
                case 'elle': mot = mot + "a";
                    break;
                case 'nous': mot = mot + "ons";

                    break;
                case 'vous': mot = mot + "ez";
                    break;
                case 'ils':
                case 'elles': mot = mot + "ont";
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
                mot = verbePrincipal

                futur()

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
                    futur()
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

                var futur = data[1]

                if(tagsPrecedents != undefined &&
                    (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ) {
                    if(singulierPlurielPrecedent == "singulier"){
                        mot = futur.troisiemePersSing
                    } else {
                        mot = futur.troisiemePersPluriel;
                    }
                }

                switch(motPrecedent){
                       case 'je': mot = futur.premierePersSing;
                           break;
                       case 'tu': mot = futur.deuxiemePersSing;
                           break;
                       case 'il':
                       case 'elle': mot = futur.troisiemePersSing;
                           break;
                       case 'nous': mot = futur.premierePersPluriel;
                           break;
                       case 'vous': mot = futur.deuxiemePersPluriel;
                           break;
                       case 'ils':
                       case 'elles': mot = futur.troisiemePersPluriel;
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