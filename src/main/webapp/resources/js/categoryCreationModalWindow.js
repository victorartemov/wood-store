 $(function(){
    $('#createCategoryModal').on('submit', function(e){
        $.ajax({
            url: /create-new-category,
            type: 'POST',
            data: $('#createCategoryModal').serialize(),
            success: function(data){
                 alert('successfully submitted')
            }
        });
        $('#createCategoryModal').modal('hide');
    });
});