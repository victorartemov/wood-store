$(document).ready(function() {
            $("button").click(function() {
                var $request = path +$('#datePicker').val();
                var $noResultsText = $('#noResultsText');
                var $allProductSum = 0;
                var $sum = 0;

                $('#dynamicTables').empty();
                $noResultsText.empty();

                $.ajax({
                    type: 'GET',
                    url: $request,
                    success: function(workday) {
                        if(workday.length == 0){
                           $noResultsText.text("Нет записей для этого дня");
                        } else {
                            var tableTitle = $('<h1> Дата: ' + workday.date + '</h1>');
                            $('#dynamicTables').append(tableTitle);

                            var table = $('<table></table>').addClass('table table-bordered table-hover');
                            var tableHeading = $(' <thead><tr><th>Название</th><th>Количество, шт</th><th>Цена, р</th><th>Стоимость, р</th></tr></thead>');
                            table.append(tableHeading);

                            $.each(workday.products, function(i, product){
                                $sum = product.category.simple == true ? product.price * product.amount : product.price * product.amount * 0.096 * product.length;
                                $allProductSum += $sum;
                                var row = $('<tbody><tr><td>' + product.title + '</td>' +
                                '<td>' + product.amount + '</td>' +
                                '<td>' + product.price + '</td>' +
                                '<td>' + $sum + '</td></tr></tbody>');
                                table.append(row);
                            });

                            $('#dynamicTables').append(table);

                            var totalSum = $('<h1> Касса: ' + $allProductSum + ' рублей</h1>');
                                                        $('#dynamicTables').append(totalSum);
                        }
                    }
                });
            });
        });