<div id="createProductModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">

        <!-- Modal content-->
        <div class="modal-content">
            <form action="/${createNewProductPath}" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Создание товара</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="productTitle">Название товара</label>
                        <input type="text" placeholder="название" style="width:115px; margin-left:10px;" id="productTitle" name="title" required>
                    </div>

                    <div class="form-group">
                        <label for="selectCategoryForModal">Категория </label>
                        <select class="btn btn-default" name="selectCategory" id="selectCategoryForModal" required>
                            <option selected disabled hidden>Категория</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="productTitle">Длина товара</label>
                        <input type="text" placeholder="длина, м" style="width:115px; margin-left:10px;" id="productLength" name="length" required>
                    </div>

                    <div class="form-group">
                        <label for="productWidth">Ширина товара</label>
                        <input type="text" placeholder="ширина, м" style="width:115px; margin-left:10px;" id="productWidth" name="width">
                    </div>

                    <div class="form-group">
                        <label for="productWeight">Вес товара</label>
                        <input type="text" placeholder="вес, кг" style="width:115px; margin-left:10px;" id="productWeight" name="weight">
                    </div>

                    <div class="form-group">
                        <label for="productTitle">Цена товара</label>
                        <input type="text" placeholder="цена, р" style="width:115px; margin-left:10px;" id="productPrice" name="price" required>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Создать</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>
                </div>
            </form>
        </div>
    </div>
</div>