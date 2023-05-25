var motPrecedent;
var tagsPrecedents;
var singulierPlurielPrecedent;
var radical;

function conjugaison(temps){

// Check du mot précédent pour la conjugaison
     motPrecedent =  tableauMots[tableauMots.length - 1]
     if (motPrecedent != null){
        motPrecedent = motPrecedent.toLowerCase()
     }

// Check mot précédent si c'est un nom pour conjugaison
tagsPrecedents =  tableauTags[tableauTags.length - 1]
singulierPlurielPrecedent = tableauSingulierPluriel[tableauSingulierPluriel.length - 1];

// Si verbe pronominal

    if(mot.startsWith("se ")){
        // Enlever "se"
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
        }
    }

    if(mot.startsWith("s'")){
            // Enlever "s'"
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
            }
        }



//Radical des verbes du premier et deuxième groupe
    if(!tags.includes('irregulier')){
        radical = mot.substring(0, mot.length - 2)
    }

// Vérification du temps
    if(temps == "present"){
        conjugaisonPresent();
    }
    if(temps == "futur"){
        conjugaisonFutur();
    }
    if(temps == "passe"){
        conjugaisonPasse();
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