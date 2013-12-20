document.observe("dom:loaded", function() {

    $('$selector').observe('change', function(event){

       var select = Event.element(event);
       window.confirm('Please confirm delete');
  //      new Ajax.Updater('$target', '$context$path', {
  //          method: 'get',
  //          parameters: {'pageAction' : 'onChangeSize', 'customerId' : select.value}
            
  //      });
        
    });
});
