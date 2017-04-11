$(document).ready(function() {

            var $result = $('#selectCategoryForModal');

            $('#selectCategoryForModal').on('click', function() {
                $.ajax({
                    type: 'GET',
                    url: "/getCategories",
                    success: function(categories) {
                        $.each(categories, function(i, category) {
                            $result.append($("<option></option>")
                            .attr("value",category.id).text(product.title));
                        });
                       }
                    }
                });
            });
        });