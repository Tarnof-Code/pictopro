$('#submitModifMot').click(function(){
    event.preventDefault();
    var formData = new FormData($('#formulaireModifMot')[0]); // Create a FormData object
    var url = "/gestionDesMots/modifierUnMot";

    $.ajax({
        type: 'POST',
        url: url,
        data: formData,
        processData: false, // Important: prevent jQuery from automatically processing the data
        contentType: false, // Important: prevent jQuery from automatically setting the content type
        success: function(data) {
            var responseArray = data.split(":");
            var motId = responseArray[0];
            var message = responseArray[1];

            $('#formModifMot').addClass("hidden");
            $('#annulModifMotButton').addClass("hidden");
            $('#modifMotButton').removeClass("hidden");
            alert(message);

            var url2 = '/gestionDesMots/mot/' + motId;
            $('#modaleInfosMots .modal-body').load(url2);

            //location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Echec de la modification');
            console.log(textStatus + ': ' + errorThrown);
        }
    });
});
