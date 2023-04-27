//Afficher les sous-catégories si les catégories en ont

$('#categorie').change(getContent);

function getContent(){
    var url = "/gestionDesMots/sousCategories/"
    var id = $("#categorie").val()
    url = url + id;

    var tailleListe = $('#categorie option:selected').attr('sousCat')

    if(tailleListe > 0) {
         $('#sousCategorie').load(url);
         $('#divSousCat').removeClass("hidden")
    } else {
         $('#divSousCat').removeClass("hidden").addClass("hidden")
    }
}
