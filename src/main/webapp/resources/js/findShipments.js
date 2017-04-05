$(document).ready(function() {
            $("button").click(function() {
                var $result = $('#shipments');
                var $request = '/getShipments?date=' +$('#datePicker').val();
                $result.empty();

                $.ajax({
                    type: 'GET',
                    url: $request,
                    success: function(shipments) {
                        $.each(shipments, function(i, shipment) {
                            $result.append('<li>' + shipment.date + '</li>');
                        });
                    }
                });
            });
        });