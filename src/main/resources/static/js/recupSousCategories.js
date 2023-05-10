//Afficher les sous-catégories dans "ajouter un mot"

$('#categorie').change(getContent);

function getContent(){
    var url = "/gestionDesMots/sousCategories/"
    var id = $("#categorie").val()
    url = url + id;
    console.log(id)

    var tailleListe = $('#categorie option:selected').attr('sousCat')

    if(tailleListe > 0) {
         $('#sousCategorie').load(url);
         $('#divSousCat').removeClass("hidden")
    } else {
         $('#divSousCat').removeClass("hidden").addClass("hidden")
    }
}


// Afficher les sous-catégories dans "echange"

$(".categorieItem").click(function(){
    var url = "/gestionDesMots/sousCategories/"
    var id = $(this).data("categorie-id");
    url = url + id;
    console.log(id)

    var tailleListe = $(this).data('size');
    console.log(tailleListe)

    if(tailleListe > 0) {
             $('#sousCategorieItem').load(url);
             $('#divSousCatItem').removeClass("hidden").addClass("d-flex")
        } else {
             $('#divSousCatItem').removeClass("d-flex").addClass("hidden")
        }
})
