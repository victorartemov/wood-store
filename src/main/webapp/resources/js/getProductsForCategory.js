$(document).ready(function() {
            $('#selectCategory').on('change', function() {
                var $result = $('#productSelect');
                var $inputQuantity = $("#inputQuantity");
                var $request = '/get-products?id=' + $( "#selectCategory" ).val();
                $result.empty();

                $result.prop('disabled', false);
                $inputQuantity.prop('disabled', false);

                $.ajax({
                    type: 'GET',
                    url: $request,
                    success: function(products) {
                        if (products.length === 0) {
                            $result.append($("<option></option>")
                            .attr("value",-1).text('Нет товаров выбранной категории'));
                            $result.prop('disabled', true);
                            $inputQuantity.prop('disabled', true);
                        } else {
                            $.each(products, function(i, product) {
                                $result.append($("<option></option>")
                                .attr("value",product.title).text(product.title));
                            });
                        }
                    }
                });
            });
        });