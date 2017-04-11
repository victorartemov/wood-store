 $(function(){
    $('#createProductModal').on('submit', function(e){
        $.ajax({
            url: /createNewProductFromModal,
            type: 'POST',
            data: $('#createProductModal').serialize(),
            success: function(data){
                 alert('successfully submitted')
            }
        });
        $('#createProductModal').modal('hide');
    });
});