let idMot;

$('a[data-bs-toggle="modal"]').click(function() {
  const id = $(this).data('id');
  console.log(id);
  idMot = id;
  var url = '/gestionDesMots/mot/' + id
  $('#modaleInfosMots .modal-body').load(url);
});

$('#modifMotButton').click(function(){
    var url ='/gestionDesMots/infosMot'
    $('#formModifMot').load(url);

    $('#formModifMot').removeClass("hidden")
    $('#annulModifMotButton').removeClass("hidden")
    $('#modifMotButton').addClass("hidden")

})

$('#annulModifMotButton').click(function(){
    $('#formModifMot').addClass("hidden")
    $('#annulModifMotButton').addClass("hidden")
    $('#modifMotButton').removeClass("hidden")
    var url = '/gestionDesMots/mot/' + idMot
    $('#modaleInfosMots .modal-body').load(url);
    console.log('Annuler')
})

$('#closeModal').click(function(){
    $('#annulModifMotButton').addClass("hidden")
    $('#modifMotButton').removeClass("hidden")
    $('#formModifMot').addClass("hidden")

})






