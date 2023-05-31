var motPrecedent;
var motMoinsDeux;
var motMoinsTrois;
var tagsPrecedents;
var tagsMoinsDeux;
var singulierPlurielPrecedent;
var radical;
var negation;
const voyelles = ["a","e","i","o","u","y"];
const pronomsVerbePronominaux = ["me","m'","te","t'","se","s'","nous","vous"]


function conjugaison(temps){

// Check des mots précédents pour la conjugaison
     motPrecedent =  tableauMots[tableauMots.length - 1]
     if (motPrecedent != null){
        motPrecedent = motPrecedent.toLowerCase()
     };
     motMoinsDeux =  tableauMots[tableauMots.length - 2]
     if (motMoinsDeux != null){
        motMoinsDeux = motMoinsDeux.toLowerCase()
     };
     motMoinsTrois =  tableauMots[tableauMots.length - 3]
     if (motMoinsTrois != null){
        motMoinsTrois = motMoinsTrois.toLowerCase()
     };

tagsPrecedents =  tableauTags[tableauTags.length - 1];
tagsMoinsDeux = tableauTags[tableauTags.length - 2];
singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];


// Si verbe pronominal

    // Enlever "se"
    if(mot.startsWith("se ")){

        var resteDuMot = mot.substring(2);

        switch(motPrecedent){
            case 'je': mot = "me" + resteDuMot
                break;
            case 'tu': mot = "te" + resteDuMot
                break;
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
        };

        // Si sujet composé d'un mot suivi de "et moi"
        if(tableauMots.length > 2){
             if(motMoinsDeux == "et" && motPrecedent == "moi"){
                mot = "nous" + resteDuMot
             }
        };


        // Si verbe avant
        switch(motMoinsDeux){
            case 'je':
            case "j'": mot = "me" + resteDuMot
                break;
            case 'tu': mot = "te" + resteDuMot
                break;
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
        };

        // Si sujet composé d'un mot suivi de "et moi"
        if(tableauMots.length > 2){
             if(motMoinsTrois == "et" && motMoinsDeux == "moi"){
                mot = "nous" + resteDuMot
             }
        };
    }

    // Enlever "s'"
    if(mot.startsWith("s'")){

            var resteDuMot = mot.substring(2);

            switch(motPrecedent){
                case 'je': mot = "m'" + resteDuMot
                    break;
                case 'tu': mot = "t'" + resteDuMot
                    break;
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
            };
             // Si sujet composé d'un mot suivi de "et moi"
            if(tableauMots.length > 2){
                 if(motMoinsDeux == "et" && motPrecedent == "moi"){
                    mot = "nous " + resteDuMot
                 }
            };

            // Si verbe avant
            switch(motMoinsDeux){
                case 'je':
                case "j'": mot = "m'" + resteDuMot
                    break;
                case 'tu': mot = "t'" + resteDuMot
                    break;
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
            };
             // Si sujet composé d'un mot suivi de "et moi"
            if(tableauMots.length > 2){
                 if(motMoinsTrois == "et" && motMoinsDeux == "moi"){
                    mot = "nous " + resteDuMot
                 }
            };
        }

    var dejaUnVerbe = false;

    // Si dans les tags précédents il y a un verbe on passe la variable dejaUnVerbe à "true" pour mettre le 2e verbe à l'infinitif
    for(let i = 0; i < tableauTags.length; i++){
        if(tableauTags[i].includes("'verbe'")){
            dejaUnVerbe = true;
        }
    }

    // Par contre s'il y a le mot "et" on la remet à false (ex : Je mange et tu dors = 2 verbes à conjuguer)
    for(let i =0; i < tableauMots.length; i++){
        if(tableauMots[i] == "et"){
            dejaUnVerbe = false;
        }
    }

//Radical des verbes du premier et deuxième groupe s'il n'y a pas déjà un verbe conjugué
    if(!tags.includes('irregulier') && dejaUnVerbe == false){
            radical = mot.substring(0, mot.length - 2)
    }

// Vérification du temps s'il n'y a pas déjà un verbe conjugué
    if(dejaUnVerbe == false){
            if(temps == "present"){
                conjugaisonPresent();
            }
            if(temps == "futur"){
                conjugaisonFutur();
            }
            if(temps == "passe"){
                conjugaisonPasse();
            }
    }

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