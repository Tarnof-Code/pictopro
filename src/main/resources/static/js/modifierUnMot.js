$('#submitModifMot').click(function(){
    event.preventDefault();
    var formData = $('#formulaireModifMot').serialize();
    var url = "/gestionDesMots/modifierUnMot"

    $.ajax({
      type: 'POST',
      url: url,
      data: formData,
      success: function(data) {
        var responseArray = data.split(":");
        var motId = responseArray[0];
        var message = responseArray[1];

        $('#formModifMot').addClass("hidden")
        $('#annulModifMotButton').addClass("hidden")
        $('#modifMotButton').removeClass("hidden")
        alert(message);

        var url2 = '/gestionDesMots/mot/' + motId
          $('#modaleInfosMots .modal-body').load(url2);

        console.log("Le mot a été modifié (ID du mot : " + motId + ")");
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert('Echec de la modification');
        console.log(textStatus + ': ' + errorThrown);
      }
    });
});
