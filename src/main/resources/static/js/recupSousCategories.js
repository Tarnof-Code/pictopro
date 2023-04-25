$('#categorie').change(getContent);

function getContent(){
    var url = "/gestionDesMots/sousCategories/"
    var id = $("#categorie").val()
    url = url + id;
    $('#sousCategorie').removeClass("hidden")
    $('#sousCategorie').load(url);
}
