$(document).ready(function() {
            $("button").click(function() {
                var $result = $('#shipments');
                var $noResultsText = $('#noResultsText');
                var $request = '/getShipments?date=' +$('#datePicker').val();
                $result.empty();
                $noResultsText.empty();

                $.ajax({
                    type: 'GET',
                    url: $request,
                    success: function(shipments) {
                        if (shipments.length === 0) {
                            $noResultsText.text("В этот день не было приходов");
                        } else {
                            $.each(shipments, function(i, shipment) {
                                $result.append('<li>' + shipment.date + '</li>');
                            });
                        }
                    }
                });
            });
        });