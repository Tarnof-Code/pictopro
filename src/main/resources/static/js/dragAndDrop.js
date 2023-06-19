  function updateDroppables() {
    $(".empty").droppable({
      accept: ".item",
      tolerance: 'pointer',
      hoverClass: 'highlight',
      drop: function(ev, ui) {
        var item = ui.draggable;
        if (!ui.draggable.closest('.empty').length) {
          item = item.clone().draggable();
        }
        var temp = item.clone(false).removeClass("ui-draggable");

        temp.removeClass('item');
        temp.addClass('item2');

        var tags = item.data('mot-tags');

        this.replaceWith(temp[0]);

        updateTableauMots();
      }
    });
  }


function dragAndDrop() {
  $(".source .item").draggable({
    revert: "invalid",
    helper: 'clone',
    start: function(ev, ui) {
      ui.helper.width($(this).width());
    }
  });

  $("#sortable").sortable({
    update: function(event,ui){
        updateTableauMots()
    }
  });

  updateDroppables();

  $(".target").on('click', '.closer', function() {
    var item = $(this).closest('.item2');
    var empty = $("<li>", {"class": "empty"});
    item.replaceWith(empty);
    updateDroppables();
    updateTableauMots();
  });

  $(".target").on('dblclick', '.item2', function() {
    var item = $(this).closest('.item2');
    var empty = $("<li>", {"class": "empty"});
    item.replaceWith(empty);
    updateDroppables();
    updateTableauMots();
  });

  $(".source .item").mousedown(function(){
      var nomMot = $(this).data("mot-nom");
      textToSpeech(nomMot); // Fonction définie dans construireUnePhrase.js
  });
}

dragAndDrop();

