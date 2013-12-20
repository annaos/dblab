jQuery(document).ready(function() {

jQuery("#selectSize").change(function (event) {
    sortTable(event);
//window.confirm('Please confirm delete');
  })
  
    jQuery("#table th a, .pagelinks a").live('click', function(event){
       
        sortTable(event);
        return false;
    })
})


function sortTable(event) {
    var link = jQuery(event.currentTarget);
    var url = link.attr('href');
         
           // callServer = window.confirm('Please confirm delete');
       
    jQuery.get(url, function(data) {
        // Update the table container with the new table
        jQuery("#tableContainer").html(data);
    });
}