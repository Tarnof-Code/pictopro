$('a[data-bs-toggle="modal"]').click(function() {
  const id = $(this).data('id');
  console.log(id);
  var url = '/gestionDesMots/mot/' + id
  $('#modaleInfosMots .modal-body').load(url);
});

$('#modifMotButton').click(function(){
    $('#formModifMot').removeClass("hidden")
    $('#annulModifMotButton').removeClass("hidden")
    $('#modifMotButton').addClass("hidden")
})

$('#annulModifMotButton').click(function(){
    $('#formModifMot').addClass("hidden")
    $('#annulModifMotButton').addClass("hidden")
    $('#modifMotButton').removeClass("hidden")
})

$('#closeModal').click(function(){
    $('#annulModifMotButton').addClass("hidden")
    $('#modifMotButton').removeClass("hidden")
    $('#formModifMot').addClass("hidden")
})




