//Afficher les sous-catégories dans "ajouter ou modifier un mot"

$('#categorie').change(getContent);

function getContent(){
    var url = "/gestionDesMots/sousCategories/"
    var id = $("#categorie").val()
    url = url + id;

    var tailleListe = $('#categorie option:selected').attr('sousCat')

	if (tailleListe > 0) {
		$('#sousCategorie').load(url);
		$('#divSousCat').removeClass("hidden")
	} else {
		$('#divSousCat').removeClass("hidden").addClass("hidden")
	}
}



// Au click sur une catégorie, afficher les sous-catégories et les mots dans "echange"

$(".categorieItem").click(function() {

    // Afficher les sous-catégories
    var url = "/gestionDesMots/sousCategories/"
    var id = $(this).data("categorie-id");
    url = url + id;

    var tailleListeSousCats = $(this).data('size');

	if (tailleListeSousCats > 0) {
		$('#sousCategorieItem').load(url, motsSousCat);
		$('#divSousCatItem').removeClass("hidden").addClass("d-flex")

	} else {
		$('#divSousCatItem').removeClass("d-flex").addClass("hidden")
	}

    // TextToSpeech des catégories
    var nomCategorie = $(this).data("categorie-nom");
    textToSpeech(nomCategorie); // Fonction définie dans construireUnePhrase.js

	// Afficher les mots
	var url2 = "/listeMotsCat/"
	url2 = url2 + id;

	var tailleListeMots = $(this).data('size2')

	if (tailleListeMots > 0 && tailleListeSousCats == 0) {
		$('#motItem').load(url2, dragAndDrop);
		$('#divMotItem').removeClass("hidden").addClass("d-flex")
	} else if (tailleListeMots > 0 && tailleListeSousCats > 0) {
		$('#motItem').load(url2, dragAndDrop);
		$('#divMotItem').removeClass("hidden").addClass("d-flex")
	}
	else {
		$('#divMotItem').removeClass("d-flex").addClass("hidden")
	}

});




// Au click sur une sous-catégorie, afficher les mots dans "echange"

function motsSousCat(){
  $(".sousCategorieItem").click(function(){
    var url = "/listeMotsSousCat/"
        var id = $(this).data("souscategorie-id");
        url = url + id;

        var tailleListeMots = $(this).data('size')

		if (tailleListeMots > 0) {
			$('#motItem').load(url, dragAndDrop);
			$('#divMotItem').removeClass("hidden").addClass("d-flex")
		} else {
			$('#divMotItem').removeClass("d-flex").addClass("hidden")
		}

	// TextToSpeech des sous-catégories
        var nomSousCategorie = $(this).data("souscategorie-nom");
        textToSpeech(nomSousCategorie); // Fonction définie dans construireUnePhrase.js

	})
}


