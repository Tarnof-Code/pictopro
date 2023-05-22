$(document).ready(function() {
  $('.iconButton').click(function() {
    var button = $(this);
    button.addClass('active clicked');
    setTimeout(function() {
      button.removeClass('active clicked');
    }, 1000);
  });
});

