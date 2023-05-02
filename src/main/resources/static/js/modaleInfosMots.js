$('a[data-bs-toggle="modal"]').click(function() {
 // $('.modal-backdrop').remove();
  const id = $(this).data('id');
  console.log(id);
  var url = '/gestionDesMots/mot/' + id
  $('#modaleInfosMots .modal-body').load(url);
});






