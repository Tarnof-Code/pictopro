//Filtres et disabled checkbox

var checkboxes = document.querySelectorAll('.tags');

checkboxes.forEach(function(checkbox) {
    window.addEventListener('load', function(){
        if(checkbox.value === 'premier_groupe' ||
           checkbox.value === 'deuxieme_groupe' ||
           checkbox.value === 'singulier' ||
           checkbox.value === 'pluriel' ||
           checkbox.value === 'masculin' ||
           checkbox.value === 'feminin' ||
           checkbox.value === 'irregulier' ||
           checkbox.value === 'auxiliaire_avoir' ||
           checkbox.value === 'auxiliaire_etre'
           ){
        checkbox.parentNode.classList.add('hidden');
        }
    })
});

checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('change', function() {

//Checkbox verbe
    if (this.value === 'verbe' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value === 'premier_groupe' ||
            checkbox.value === 'deuxieme_groupe' ||
            checkbox.value === 'irregulier' ||
            checkbox.value === 'auxiliaire_avoir' ||
            checkbox.value === 'auxiliaire_etre'
           ) {
           checkbox.parentNode.classList.remove('hidden');
        } else if (checkbox.value != 'verbe') {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });

    }
    if(this.value === 'verbe' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === 'premier_groupe' ||
                  checkbox.value === 'deuxieme_groupe' ||
                  checkbox.value === 'irregulier' ||
                  checkbox.value === 'auxiliaire_avoir' ||
                  checkbox.value === 'auxiliaire_etre'
          ) {
        checkbox.parentNode.classList.add('hidden');
        checkbox.checked = false;
      }
      checkbox.parentNode.classList.remove('greyColor')
      checkbox.disabled = false;
      });
    }

//Checkbox premier_groupe
    if (this.value === 'premier_groupe' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'premier_groupe' &&
            checkbox.value != 'verbe' &&
            checkbox.value != 'auxiliaire_avoir' &&
            checkbox.value != 'auxiliaire_etre'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'premier_groupe' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "deuxieme_groupe" ||
          checkbox.value === "irregulier"
      ){checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox deuxieme_groupe
    if (this.value === 'deuxieme_groupe' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'deuxieme_groupe' &&
            checkbox.value != 'verbe' &&
            checkbox.value != 'auxiliaire_avoir' &&
            checkbox.value != 'auxiliaire_etre'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'deuxieme_groupe' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "premier_groupe" ||
          checkbox.value === "irregulier"
      ){checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox irregulier
    if (this.value === 'irregulier' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'nom' &&
            checkbox.value != 'adjectif' &&
            checkbox.value != 'masculin' &&
            checkbox.value != 'feminin' &&
            checkbox.value != 'irregulier' &&
            checkbox.value != 'verbe' &&
            checkbox.value != 'auxiliaire_avoir' &&
            checkbox.value != 'auxiliaire_etre'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'irregulier' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "premier_groupe" ||
          checkbox.value === "deuxieme_groupe"
      ){checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox auxiliaire_avoir
    if (this.value === 'auxiliaire_avoir' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'verbe' &&
            checkbox.value != 'premier_groupe' &&
            checkbox.value != 'deuxieme_groupe' &&
            checkbox.value != 'auxiliaire_avoir' &&
            checkbox.value != 'irregulier'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'auxiliaire_avoir' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "auxiliaire_etre"){
        checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox auxiliaire_etre
    if (this.value === 'auxiliaire_etre' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'verbe' &&
            checkbox.value != 'premier_groupe' &&
            checkbox.value != 'deuxieme_groupe' &&
            checkbox.value != 'auxiliaire_etre' &&
            checkbox.value != 'irregulier'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'auxiliaire_etre' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "auxiliaire_avoir"){
        checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }


//Checkbox pronom_ou_determinant
        if (this.value === 'pronom_ou_determinant' && this.checked) {
          checkboxes.forEach(function(checkbox) {
            if (checkbox.value === 'masculin' ||
                checkbox.value === 'feminin' ||
                checkbox.value === 'singulier' ||
                checkbox.value === 'pluriel'
               ) {
               checkbox.parentNode.classList.remove('hidden');
            } else if (checkbox.value != 'pronom_ou_determinant') {
               checkbox.parentNode.classList.add('greyColor')
               checkbox.disabled = true;
            }
          });
        }
        if(this.value === 'pronom_ou_determinant' && !this.checked){
          checkboxes.forEach(function(checkbox) {
          if (checkbox.value === 'masculin' ||
              checkbox.value === 'feminin' ||
              checkbox.value === 'singulier' ||
              checkbox.value === 'pluriel'
              ) {
            checkbox.parentNode.classList.add('hidden');
            checkbox.checked = false;
          }
          checkbox.parentNode.classList.remove('greyColor')
          checkbox.disabled = false;
          });
        }

//Checkbox singulier
    if (this.value === 'singulier' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'pronom_ou_determinant' &&
            checkbox.value != 'singulier' &&
            checkbox.value != 'masculin' &&
            checkbox.value != 'feminin'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'singulier' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "pluriel"){
        checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox pluriel
    if (this.value === 'pluriel' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'pronom_ou_determinant' &&
            checkbox.value != 'pluriel' &&
            checkbox.value != 'masculin' &&
            checkbox.value != 'feminin'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'pluriel' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "singulier"){
        checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox masculin
    if (this.value === 'masculin' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'nom' &&
            checkbox.value != 'pronom_ou_determinant' &&
            checkbox.value != 'pluriel' &&
            checkbox.value != 'masculin' &&
            checkbox.value != 'singulier' &&
            checkbox.value != 'irregulier'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'masculin' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "feminin"){
        checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }

//Checkbox feminin
    if (this.value === 'feminin' && this.checked) {
      checkboxes.forEach(function(checkbox) {
        if (checkbox.value != 'nom' &&
            checkbox.value != 'pronom_ou_determinant' &&
            checkbox.value != 'pluriel' &&
            checkbox.value != 'feminin' &&
            checkbox.value != 'singulier' &&
            checkbox.value != 'irregulier'
            ) {
           checkbox.parentNode.classList.add('greyColor')
           checkbox.disabled = true;
        }
      });
    }
    if(this.value === 'feminin' && !this.checked){
      checkboxes.forEach(function(checkbox) {
      if (checkbox.value === "masculin"){
        checkbox.parentNode.classList.remove('greyColor')
        checkbox.disabled = false;}
      });
    }



//Checkbox nom
        if (this.value === 'nom' && this.checked) {
          checkboxes.forEach(function(checkbox) {
            if (checkbox.value === 'masculin' ||
                checkbox.value === 'feminin' ||
                checkbox.value === 'irregulier'
               ) {
               checkbox.parentNode.classList.remove('hidden');
            } else if (checkbox.value != 'nom') {
               checkbox.parentNode.classList.add('greyColor')
               checkbox.disabled = true;
            }
          });
        }
        if(this.value === 'nom' && !this.checked){
          checkboxes.forEach(function(checkbox) {
          if (checkbox.value === 'masculin' ||
              checkbox.value === 'feminin' ||
              checkbox.value === 'irregulier'
              ) {
            checkbox.parentNode.classList.add('hidden');
            checkbox.checked = false;
          }
          checkbox.parentNode.classList.remove('greyColor')
          checkbox.disabled = false;
          });
        }

//Checkbox adjectif
        if (this.value === 'adjectif' && this.checked) {
          checkboxes.forEach(function(checkbox) {
            if (checkbox.value === 'irregulier') {
               checkbox.parentNode.classList.remove('hidden');
            } else if (checkbox.value != 'adjectif') {
               checkbox.parentNode.classList.add('greyColor')
               checkbox.disabled = true;
            }
          });
        }
        if(this.value === 'adjectif' && !this.checked){
          checkboxes.forEach(function(checkbox) {
          if (checkbox.value === 'irregulier') {
            checkbox.parentNode.classList.add('hidden');
            checkbox.checked = false;
          }
          checkbox.parentNode.classList.remove('greyColor')
          checkbox.disabled = false;
          });
        }

//Checkbox nombre
        if (this.value === 'nombre' && this.checked) {
          checkboxes.forEach(function(checkbox) {
           if (checkbox.value != 'nombre') {
               checkbox.parentNode.classList.add('greyColor')
               checkbox.disabled = true;
            }
          });
        }
        if(this.value === 'nombre' && !this.checked){
          checkboxes.forEach(function(checkbox) {
              checkbox.parentNode.classList.remove('greyColor')
              checkbox.disabled = false;
          });
        }

//Checkbox invariable
        if (this.value === 'invariable' && this.checked) {
          checkboxes.forEach(function(checkbox) {
           if (checkbox.value != 'invariable') {
               checkbox.parentNode.classList.add('greyColor')
               checkbox.disabled = true;
            }
          });
        }
        if(this.value === 'invariable' && !this.checked){
          checkboxes.forEach(function(checkbox) {
              checkbox.parentNode.classList.remove('greyColor')
              checkbox.disabled = false;
          });
        }

  });
});

//Affichage formulaire pour verbes irréguliers
var verbCheckbox = document.getElementById("verbe");
var irregulierCheckbox = document.getElementById("irregulier");
var verbForm = document.getElementById("verbForm");

function checkBoxesVerbAndIrregulier() {
  if (verbCheckbox.checked && irregulierCheckbox.checked) {
    verbForm.parentNode.classList.remove("hidden");
  } else {
    verbForm.parentNode.classList.add("hidden");
  }
}
verbCheckbox.addEventListener("change", checkBoxesVerbAndIrregulier);
irregulierCheckbox.addEventListener("change", checkBoxesVerbAndIrregulier);



//Affichage formulaire pour les noms avec un pluriel irrégulier
var nomCheckbox = document.getElementById("nom");
var nomForm = document.getElementById("nomForm");

function checkBoxesNomAndIrregulier() {
  if (nomCheckbox.checked && irregulierCheckbox.checked) {
    nomForm.parentNode.classList.remove("hidden");
  } else {
    nomForm.parentNode.classList.add("hidden");
  }
}
nomCheckbox.addEventListener("change", checkBoxesNomAndIrregulier);
irregulierCheckbox.addEventListener("change", checkBoxesNomAndIrregulier);



//Affichage formulaire pour les adjectifs avec un féminin et/ou un pluriel irrégulier
var adjectifCheckbox = document.getElementById("adjectif");
var adjectifForm = document.getElementById("adjectifForm");

function checkBoxesAdjectifAndIrregulier() {
  if (adjectifCheckbox.checked && irregulierCheckbox.checked) {
    adjectifForm.parentNode.classList.remove("hidden");
  } else {
    adjectifForm.parentNode.classList.add("hidden");
  }
}
adjectifCheckbox.addEventListener("change", checkBoxesAdjectifAndIrregulier);
irregulierCheckbox.addEventListener("change", checkBoxesAdjectifAndIrregulier);


