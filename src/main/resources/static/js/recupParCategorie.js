//Afficher les sous-catégories dans "ajouter ou modifier un mot"

$('#categorie').change(getContent);

function getContent(){
    var url = "/gestionDesMots/sousCategories/"
    var id = $("#categorie").val()
    url = url + id;
    console.log(id)

    var tailleListe = $('#categorie option:selected').attr('sousCat')
    console.log(tailleListe)

    if(tailleListe > 0) {
         $('#sousCategorie').load(url);
         $('#divSousCat').removeClass("hidden")
    } else {
         $('#divSousCat').removeClass("hidden").addClass("hidden")
    }
}



// Afficher les sous-catégories et les mots dans "echange"

$(".categorieItem").click(function(){

    // Afficher les sous-catégories
    var url = "/gestionDesMots/sousCategories/"
    var id = $(this).data("categorie-id");
    url = url + id;
    console.log("click sur la catégorie " + id)
    var tailleListeSousCats = $(this).data('size');

    if(tailleListeSousCats > 0) {
             $('#sousCategorieItem').load(url,motsSousCat);
             $('#divSousCatItem').removeClass("hidden").addClass("d-flex")

        } else {
             $('#divSousCatItem').removeClass("d-flex").addClass("hidden")
        }

    // Afficher les mots
    var url2 = "/listeMotsCat/"
    url2 = url2 + id;

    var tailleListeMots = $(this).data('size2')

    if(tailleListeMots > 0 && tailleListeSousCats == 0) {
        $('#motItem').load(url2,dragAndDrop);
        $('#divMotItem').removeClass("hidden").addClass("d-flex")
    } else {
        $('#divMotItem').removeClass("d-flex").addClass("hidden")
    }

});




// Au click sur une sous-catégorie, afficher les mots dans "echange"
// Fonction appelée après le load des sous-catégories

function motsSousCat(){
  $(".sousCategorieItem").click(function(){
    var url = "/listeMotsSousCat/"
        var id = $(this).data("souscategorie-id");
        url = url + id;
        console.log("click sur la sous-catégorie " + id)

        var tailleListeMots = $(this).data('size')
        console.log(tailleListeMots)

        if(tailleListeMots > 0) {
            $('#motItem').load(url,dragAndDrop);
            $('#divMotItem').removeClass("hidden").addClass("d-flex")
        } else {
            $('#divMotItem').removeClass("d-flex").addClass("hidden")
        }
    })
}


