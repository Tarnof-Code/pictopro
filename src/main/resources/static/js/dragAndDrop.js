 $(".source .item").draggable({
      revert: "invalid",
      helper: 'clone',
      start: function(ev, ui) {
        ui.helper.width($(this).width());
      } // ensure helper width
    });
    $("#sortable").sortable({
      revert: true
    });

    function updateDroppables() {
      $(".empty").droppable({
        accept: ".item",
        tolerance: 'pointer',
        hoverClass: 'highlight',
        drop: function(ev, ui) {
          var item = ui.draggable;
          if (!ui.draggable.closest('.empty').length) item = item.clone().draggable(); // if item was dragged from the source list - clone it
          var temp = item.clone(false)
            .removeClass("ui-draggable");

          temp.removeClass('item')
          temp.addClass('item2')

          this.replaceWith(temp[0])

        }
      });
    }

    updateDroppables();

    $(".target").on('click', '.closer', function() {
      var item = $(this).closest('.item2');
      var empty = $("<li>",{"class":"empty"});
      item.replaceWith(empty);
      updateDroppables();
    });