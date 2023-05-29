function negation(){

   const voyelles = ["a","e","i","o","u","y"];
   let verbe;

    for(let i in tableauMots){
       if(tableauMots[i].endsWith("'")){
          tableauMots[i] = tableauMots[i].replace("'", "e");
       }
    }

    for(let i=0; i < tableauTags.length; i++){

        if(tableauTags[i].includes("'verbe'")){

            verbe = tableauMots[i];

            if(verbe.split(' ').length > 1) {
               var motsDuVerbe = verbe.split(' ');
               var reste;

               if(motsDuVerbe[0].length < 3) {
                    verbe = motsDuVerbe[0] + " " + motsDuVerbe[1];
                    reste = motsDuVerbe.slice(2).join(' ');
               } else {
                    verbe = motsDuVerbe[0];
                    reste = motsDuVerbe.slice(1).join(' ');
               }

            }

            const premiereLettre = verbe[0];

            if(voyelles.includes(premiereLettre)){
                tableauMots[i] = "n'" + verbe + " pas";
            } else {
                tableauMots[i] = "ne " + verbe + " pas" + " ";
            }


            if(reste != undefined) {
                tableauMots[i] = tableauMots[i] + " " + reste;
            }

            phrase = tableauMots.join(' ')
            $("#contenuPhrase").text(phrase);
        }
    }
    negationAppelee = true;
}