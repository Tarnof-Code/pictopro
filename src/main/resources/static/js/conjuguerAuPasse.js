function conjugaisonPasse(){

var auxiliaire;
var tagsMoinsTrois;

if (tags.includes("auxiliaire_avoir")) {
    auxiliaire = ["ai","as","a","avons","avez","ont"];
} else if (tags.includes("auxiliaire_etre")) {
    auxiliaire = ["suis","es","est","sommes","êtes","sont"];
}

// Pour les verbes pronominaux
  if(radical != undefined){
    var tableauMotsRadical = radical.split(/[\s']/); //
    var premierMot = tableauMotsRadical[0];

    if(premierMot.length < 3 || premierMot == "nous" || premierMot == "vous"){
        var pronom = premierMot
        if(pronom.length === 1){
            pronom = pronom + "e"
        }

        if(tableauMotsRadical.length > 1){
            for (var i in auxiliaire) {
                 // Si les mots qui se suivent finissent et commencent par une voyelle

                 const derniereLettre = pronom.slice(-1)
                 const premiereLettre = auxiliaire[i][0]
                 if(voyelles.includes(derniereLettre) && voyelles.includes(premiereLettre)){
                     var nouveauPronom = pronom.slice(0, -1) + "'"; // On change le pronom avec une apostrophe
                     auxiliaire[i] = nouveauPronom + " " + auxiliaire[i]
                 } else {
                    auxiliaire[i] = pronom + " " + auxiliaire[i]
                 }
            }
            radical = tableauMotsRadical[1]
        }
    }
  }

// Pour ne pas accorder le participe passé avec les verbes du style "se laver les dents"
    var mettreParticipeAuSingulier = false

// Conjugaison au passé-composé pour les verbes du 1er groupe
     if(tags.includes("premier_groupe")){

        // Fonction de conjugaison
        function passe(){

            var participePasse;

            if(tags.includes("auxiliaire_etre") && mettreParticipeAuSingulier == false){
                if(femininMasculinPrecedent == "feminin"){
                    participePasse = radical + "ée"
                } else {
                    participePasse = radical + "é"
                }
                if(singulierPlurielPrecedent == "pluriel") {
                    participePasse = participePasse + "s"
                }
                if(tableauMots.length > 2){
                    if(motMoinsDeux == "et" && motPrecedent == "moi"){
                        participePasse = participePasse + "s"
                    }
                }
            } else {
                participePasse = radical + "é"
            }


            if(tagsPrecedents != undefined &&
            (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ){
                    if(singulierPlurielPrecedent == "singulier"){
                        mot = auxiliaire[2] + " " + participePasse;
                    } else {
                        mot = auxiliaire[5] + " " + participePasse;
                    }
            }

             switch(motPrecedent){
                case 'je': mot = auxiliaire[0] + " " + participePasse;
                    break;
                case 'tu': mot = auxiliaire[1] + " " + participePasse;
                    break;
                case 'il':
                case 'elle': mot = auxiliaire[2] + " " + participePasse;
                    break;
                case 'nous': mot = auxiliaire[3] + " " + participePasse;

                    break;
                case 'vous': mot = auxiliaire[4] + " " + participePasse;
                    break;
                case 'ils':
                case 'elles': mot = auxiliaire[5] + " " + participePasse;
                    break;
                default : mot = mot;
            };

             // Si le sujet est composé de 2 mots reliés par "et"
             if(tableauMots.length > 2){
                 if(motMoinsDeux == "et" && motPrecedent == "moi"){
                         mot = auxiliaire[3] + " " + participePasse;
                 }
                 if(motMoinsDeux == "et" && (motPrecedent == "elle" || motPrecedent == "eux")){
                     mot = auxiliaire[5] + " " + participePasse;
                 }
             };
             // A la négative
              if(negation == true && tagsPrecedents != undefined && !tagsPrecedents.includes("'verbe'")){
                 mettreAuNegatif();
              };

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
                //Si verbe pronominal et mots après
                var tableauMotsVerbePrincipal = verbePrincipal.split(' ')

                if(tableauMotsVerbePrincipal.length > 1){
                    tableauMotsVerbePrincipal.splice(0,1)
                    verbePrincipal = tableauMotsVerbePrincipal[0]
                    mettreParticipeAuSingulier = true;
                }
                radical = verbePrincipal.substring(0, verbePrincipal.length - 2)

                passe();

                 mot = mot + ' ' + resteDeLaPhrase;

            } else {

            // Si pas d'autres mots après le verbe
                    passe();
            }
     };

// Conjugaison au passé-composé pour les verbes du 2e groupe
     if(tags.includes("deuxieme_groupe")){

        // Fonction de conjugaison
        function passe(){

         var participePasse;

         if(tags.includes("auxiliaire_etre") && mettreParticipeAuSingulier == false){
             if(femininMasculinPrecedent == "feminin"){
                 participePasse = radical + "ie"
             } else {
                 participePasse = radical + "i"
             }
             if(singulierPlurielPrecedent == "pluriel") {
                 participePasse = participePasse + "s"
             }
             if(tableauMots.length > 2){
                 if(motMoinsDeux == "et" && motPrecedent == "moi"){
                     participePasse = participePasse + "s"
                 }
             }
         } else {
             participePasse = radical + "i"
         }

            if(tagsPrecedents != undefined &&
            (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ){
                    if(singulierPlurielPrecedent == "singulier"){
                        mot = auxiliaire[2] + " " + participePasse;
                    } else {
                        mot = auxiliaire[5] + " " + participePasse;
                    }
            }

             switch(motPrecedent){
                case 'je': mot = auxiliaire[0] + " " + participePasse;
                    break;
                case 'tu': mot = auxiliaire[1] + " " + participePasse;
                    break;
                case 'il':
                case 'elle': mot = auxiliaire[2] + " " + participePasse;
                    break;
                case 'nous': mot = auxiliaire[3] + " " + participePasse;

                    break;
                case 'vous': mot = auxiliaire[4] + " " + participePasse;
                    break;
                case 'ils':
                case 'elles': mot = auxiliaire[5] + " " + participePasse;
                    break;
                default : mot = mot;
            };

             // Si le sujet est composé de 2 mots reliés par "et"
             if(tableauMots.length > 2){
                 if(motMoinsDeux == "et" && motPrecedent == "moi"){
                         mot = auxiliaire[3] + " " + participePasse;
                 }
                 if(motMoinsDeux == "et" && (motPrecedent == "elle" || motPrecedent == "eux")){
                     mot = auxiliaire[5] + " " + participePasse;
                 }
             };
             // A la négative
              if(negation == true && tagsPrecedents != undefined && !tagsPrecedents.includes("'verbe'")){
                 mettreAuNegatif();
              };
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
             //Si verbe pronominal et mots après
                var tableauMotsVerbePrincipal = verbePrincipal.split(' ')
                if(tableauMotsVerbePrincipal.length > 1){
                    tableauMotsVerbePrincipal.splice(0,1)
                    verbePrincipal = tableauMotsVerbePrincipal[0]
                    mettreParticipeAuSingulier = true;
                }

            radical = verbePrincipal.substring(0, verbePrincipal.length - 2)

            passe();

           mot = mot + ' ' + resteDeLaPhrase;

        } else {

        // Si pas d'autres mots après le verbe
                passe();
        }
     };



      // Conjugaison pour les verbes du 3e groupe et verbes irréguliers
      if(tags.includes("irregulier")){
            //Récupérer le pronom si verbe pronominal et le mettre devant l'auxiliaire
            var splitMot = mot.split(/[\s']/);

            // S'il y a un pronom
            if(splitMot.length > 1 && (splitMot[0].length < 3 || splitMot[0] == "nous" | splitMot[0] == "vous")){

                let pronomPerso = splitMot[0];

                if(pronomPerso.length === 1) {
                    pronomPerso = pronomPerso + "e"
                }

                for(let i in auxiliaire){
                    // Pronom finit par une voyelle et auxiliaire commence par une voyelle
                    const voyelles = ["a","e","i","o","u","y"]
                    const derniereLettre = pronomPerso.slice(-1)
                    const premiereLettre = auxiliaire[i][0]
                    if(voyelles.includes(derniereLettre) && voyelles.includes(premiereLettre)){
                        var nouveauPronom = pronomPerso.slice(0, -1) + "'"; // On change le pronom avec une apostrophe
                        auxiliaire[i] = nouveauPronom  + auxiliaire[i]
                    } else {
                       auxiliaire[i] = pronomPerso + " " + auxiliaire[i]
                    }
                }
            }

            $.ajax({
              url: '/getIrregulier/' + idMot,
              method: 'GET',
              dataType: 'json',
              async : false,
              success: function(data) {

                var participePasse = data.participePasse
                var reste; // Si d'autres mots après le verbe
                tagsMoinsTrois = tableauTags[tableauTags.length - 3]; // Tags du moi avant le "et"

                // Accord du participe si auxiliaire être
                if(tags.includes("auxiliaire_etre")){
                    // Si plusieurs mots, séparer participe et le reste
                    var tableauMotsParticipe = participePasse.split(" ");
                    participePasse = tableauMotsParticipe[0];
                    reste = tableauMotsParticipe.slice(1).join(" ");

                    //Si mot précédent est féminin, ajouter un "e" sauf si mot masculin avant "et"
                    if(femininMasculinPrecedent == "feminin" && tagsMoinsTrois != undefined){
                        if(motMoinsDeux == "et" && tagsMoinsTrois.includes("'masculin'")){
                            participePasse = participePasse
                        } else {
                            participePasse = participePasse + "e"
                        }
                    }

                    //Si mot précédent est pluriel, ajouter un "s"
                    if(singulierPlurielPrecedent == "pluriel") {
                        participePasse = participePasse + "s"
                    }

                    // "....et moi", ajouter un "s"
                    if(tableauMots.length > 2){
                        if(motMoinsDeux == "et" && motPrecedent == "moi"){
                            participePasse = participePasse + "s"
                        }
                    }

                }

                if(tagsPrecedents != undefined &&
                    (tagsPrecedents.includes("'nom'")||tagsPrecedents.includes("'adjectif'")) ) {
                    if(singulierPlurielPrecedent == "singulier"){
                        mot = mot = auxiliaire[2] + " " + participePasse;
                    } else {
                        mot = auxiliaire[5] + " " + participePasse;
                    }
                }

                switch(motPrecedent){
                       case 'je': mot = auxiliaire[0] + " " + participePasse;
                           break;
                       case 'tu': mot = auxiliaire[1] + " " + participePasse;
                           break;
                       case 'il':
                       case 'elle': mot = auxiliaire[2] + " " + participePasse;
                           break;
                       case 'nous': mot = auxiliaire[3] + " " + participePasse;
                           break;
                       case 'vous': mot = auxiliaire[4] + " " + participePasse;
                           break;
                       case 'ils':
                       case 'elles': mot = auxiliaire[5] + " " + participePasse;
                           break;
                       default : mot = mot;
               };

                // Si le sujet est composé de 2 mots reliés par "et"
                if(tableauMots.length > 2){
                    if(motMoinsDeux == "et" && motPrecedent == "moi"){
                            mot = auxiliaire[3] + " " + participePasse;
                    }
                    if(motMoinsDeux == "et" && (motPrecedent == "elle" || motPrecedent == "eux")){
                        mot = auxiliaire[5] + " " + participePasse;
                    }
                };

               if(reste != undefined){
                    mot = mot + " " + reste
               }
                    // A la négative
                  if(negation == true && tagsPrecedents != undefined && !tagsPrecedents.includes("'verbe'")){
                     mettreAuNegatif();
                  };
              },
              error: function(error) {
                console.error(error);
              }
            });

       };

}