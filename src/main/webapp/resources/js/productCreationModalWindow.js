 $(function(){
    $('#createProductModal').on('submit', function(e){
        $.ajax({
            url: /create-new-product-from-modal,
            type: 'POST',
            data: $('#createProductModal').serialize(),
            success: function(data){
                 alert('successfully submitted')
            }
        });
        $('#createProductModal').modal('hide');
    });
});