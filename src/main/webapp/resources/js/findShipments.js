$(document).ready(function() {
            $("button").click(function() {
                var $result = $('#shipments');
                var $noResultsText = $('#noResultsText');
                var $request = '/getShipments?date=' +$('#datePicker').val();
                $result.empty();
                $noResultsText.empty();
                $('#dynamicTables').empty();

                $.ajax({
                    type: 'GET',
                    url: $request,
                    success: function(shipments) {
                        if (shipments.length === 0) {
                            $noResultsText.text("В этот день не было приходов");
                        } else {
                            $.each(shipments, function(i, shipment) {
                                var tableTitle = $('<h1> Дата: ' + shipment.date + '</h1>');
                                $('#dynamicTables').append(tableTitle);

                                var table = $('<table></table>').addClass('table table-bordered table-hover');
                                var tableHeading = $(' <thead><tr><th>Название</th><th>Количество, шт</th><th>Цена, р</th><th>Стоимость, р</th></tr></thead>');
                                table.append(tableHeading);

                                //iterate through nested list of products in json
                                $.each(shipment.products, function(i, product){

                                    var sum = product.category.simple == true ? product.price * product.amount : product.price * product.amount * 0.096 * product.length;

                                    var row = $('<tbody><tr><td>' + product.title + '</td>' +
                                    '<td>' + product.amount + '</td>' +
                                    '<td>' + product.price + '</td>' +
                                    '<td>' + sum + '</td></tr></tbody>');
                                    table.append(row);
                                });

                                $('#dynamicTables').append(table);
                            });
                        }
                    }
                });
            });
        });