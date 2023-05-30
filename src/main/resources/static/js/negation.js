function mettreAuNegatif(){
    var pronoms = ["me","t'","s'","nous","vous","se"]

    if(mot.split(' ').length > 1) {
       var motsDuVerbe = mot.split(' ');
       var reste;
        if(pronoms.includes(motsDuVerbe[0])){
            mot = motsDuVerbe[0] + " " + motsDuVerbe[1];
            reste = motsDuVerbe.slice(2).join(' ');
        } else {
            mot = motsDuVerbe[0];
            reste = motsDuVerbe.slice(1).join(' ');
        }

    }
    const voyelles = ["a","e","i","o","u","y"];
    var premiereLettre = mot[0];
    if(voyelles.includes(premiereLettre)){
        mot = "n'" + mot + " pas";
    } else {
        mot = "ne " + mot + " pas" + " ";
    }
    if(reste != undefined) {
        mot = mot + " " + reste;
    }
    negatif = false;
};

