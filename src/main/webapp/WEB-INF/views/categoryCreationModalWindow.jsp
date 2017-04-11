﻿<div id="createCategoryModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">

        <!-- Modal content-->
        <div class="modal-content">
            <form action="/${createNewCategoryPath}" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Создание категории</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="categoryTitle">Название категории</label>
                        <input type="text" placeholder="название" style="width:115px;" id="categoryTitle" name="title">
                    </div>
                    <div class="form-group">
                        <label for="categoryType">Тип категории</label>
                        <select class="btn btn-default" name="categoryType" id="categoryType">
                            <option value="0">Штучные товары</option>
                            <option value="1">Квадратура</option>
                        </select>
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