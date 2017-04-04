function findShipments() {

    var select = $('#shipmentsSelect');
    var request = '/getShipments?date=' + $('#datePicker').val();

    var b = document.getElementById("findClickButton");

    $.ajax(request, {
        method : 'get',
        success: function(data) {

        }
    });
 }