$(document).ready(function() {
  // bind all links to use pjaxr if it's supported on initial page request
  if ($.support.pjaxr) {
    $(document).pjaxr('a');
  }
}).on('pjaxr:done', function() {
  // bind all links to use pjaxr if it's supported after pjaxr request
  $(document).pjaxr('a');
});
