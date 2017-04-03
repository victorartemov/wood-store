function selectProductsForCategory(selectObject) {

        select = document.getElementById("productSelect");
        inputQuantity = document.getElementById("inputQuantity");

        inputQuantity.defaultValue = "0";
        select.disabled = false;
        inputQuantity.disabled = false;

        var value = selectObject.value;

        var request = '/getProducts?id=' + value;

        $.ajax(request, {
            method : 'get',
            success: function(data) {

                var size = data.length;
                var result = "";

                if(size != 0){
                    for (var i=0; i!=size; ++i) {
                         result += "<option>";
                         result += data[i].title;
                         result += "</option>";
                    }
                    $('#productSelect').html(result);
                } else{
                    var option = document.createElement('option');
                    option.innerHTML = "Нет товаров выбранной категории";
                    option.value = "1";
                    select.appendChild(option);
                    select.value = 1;

                    select.disabled = true;
                    inputQuantity.disabled = true;
                }
            }
        });
    }