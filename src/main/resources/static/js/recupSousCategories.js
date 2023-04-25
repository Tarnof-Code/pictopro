$('#categorie').change(getContent);

function getContent(){
    var url = "/gestionDesMots/sousCategories/"
    var liste = $("#categorie")
    var id = $("#categorie option:selected").attr("categorieId")
    url = url + id;
    $('#sousCategorie').load(url);
}
