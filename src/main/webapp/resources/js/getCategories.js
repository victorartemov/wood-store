$(document).ready(function() {
        var $result = $('#selectCategoryForModal');

        $('#createProductModal').on('shown.bs.modal', function() {
            $result.empty();
           $.ajax({
                type: 'GET',
                url: '/get-categories',
                success: function(categories) {
                        if (categories.length === 0) {
                            $result.append($("<option></option>")
                            .attr("value",-1).text('Empty'));
                        } else {
                            $.each(categories, function(i, category) {
                                $result.append($("<option></option>")
                                .attr("value",category.id).text(category.title));
                            });
                        }
                    }
            });
        });
    });